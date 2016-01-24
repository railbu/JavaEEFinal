package action;

import java.io.File;
import java.util.Date;
import model.Student;
import model.User;
import service.StudentService;
import service.UserService;
import service.utils.MD5Util;
import service.utils.SendMail;
import workers.UserAcQueue;
import workers.UserActMa;
import action.support.Message;
import action.support.MessageType;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class SRegisterAction extends ActionSupport implements Preparable,ModelDriven<Student>{

	private static final long serialVersionUID = -7288870851052862957L;
	private Student student;
	private String passwordAgain;
	private Message message;
	private String username;
	private File image;
	private String type=null;
	private String code;
	
	public String input(){
		return INPUT;
	}
	
	public String submit(){
	
		this.validateRegisterInfo();
		
		String code = (String) ActionContext.getContext().getSession().get("code");
		
		code = code.toLowerCase();
		this.code = this.code.toLowerCase();
		
		if (!this.code.equals(code)) {
			super.addFieldError("uPerror", "验证码输入有误！");
			return INPUT;
		}
		
		if(!super.getFieldErrors().isEmpty())
			return INPUT;
		else{
			User user = new User();
			try{
				
				user.setUsername(student.getUsername());
				user.setPassword(MD5Util.MD5(student.getPassword()));
				user.setType("1");
				
				//邮箱验证部分
				String vid = MD5Util.MD5(String.valueOf(new Date().getTime()));
				String url="请点击下面的链接进行激活\n"+
						"http://localhost:8080/JavaEEFinal/ractive_valid.action?vid="+vid; 
				SendMail.sendmail(url, student.getEmail());
				
				user.setUrl(vid);
				
				UserService.register(user);
				StudentService.register(student);
//				设置线程启动，控制激活有效时间
				UserAcQueue uaq = UserAcQueue.getuaq();
				uaq.sleepTime.add(new Date().getTime());
				uaq.vid.add(vid);
				UserActMa uam = UserActMa.getUserActMa();
				
				if(!uam.isAlive()){
					System.out.println("控制用户激活的线程启动");
					uam.start();
				}
				
				synchronized(uaq){ 
					uaq.notifyAll();;
				}
					
				
//				//临时注册
//				UserActMaTmp uat = new UserActMaTmp(1000*40, vid);
//				uat.start();
				
			}catch(Exception e){
				super.addActionError(e.getMessage());
				e.printStackTrace();
				return INPUT;
			}
			
			
			Integer count = (Integer)ActionContext.getContext().getApplication().get("register.count");
			if(count == null)
				count = 0;			
			count ++;
			
			ActionContext.getContext().getApplication().put("register.count",count);
			this.message = new Message("注册成功,请前往邮箱激活","/index.jsp",MessageType.success);
			return SUCCESS;
		}
	}

	private void validateRegisterInfo() {
		if(StringUtils.isBlank(this.student.getUsername())){
			super.addFieldError("username", "用户名不能为空");
			return;
		}
		
		if(UserService.findUser(student.getUsername())!=null){
			super.addFieldError("usernameRepeat", "该用户名已被使用");
			return;	
		}
		
		if(StringUtils.isBlank(this.student.getPassword())){
			super.addFieldError("password", "密码不能为空");
			return;
		}else if(!this.student.getPassword().equals(passwordAgain)){
			super.addFieldError("passwordAgain", "两次输入密码不一致");
			return;
		}
		
		if(StringUtils.isBlank(this.student.getEmail())){
			super.addFieldError("email", "Email不能为空");
			return;
		}
		
		if(type!=null && type.equals("3") && StringUtils.isBlank(this.student.getSchool())){
			super.addFieldError("email", "学校不能为空");
			return;
		}
	}
	
	@Override
	public void prepare() throws java.lang.Exception{
		this.student = new Student();
	}

	@Override
	public Student getModel() {
		return this.student;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

	public Message getMessage() {
		return message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

}
