package action;

import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import model.User;
import service.StudentService;
import service.TeacherService;
import service.UserService;
import service.utils.MD5Util;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class LoginAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private String password;
	private String username;
	private String type;
	private String code;
	private String[] re={"stuUser","teaUser","visUser"};
	private User user = null;
	
	
	
	//为安卓客户端登陆用的发放
	public void loginAn(){
		System.out.println("anzhuo有请求了");
		user = UserService.findUser(username);
		Gson gson = new Gson();
		String message;
		System.out.println("u="+username);
		System.out.println("p="+password);
		
		if(user!=null && (MD5Util.MD5(password)).equals(user.getPassword())){
			message = "true";
		}else{
			message = "用户名或密码不对";
		}
		System.out.println(message);
		java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<String>(){  
        }.getType();  
        String beanStringToJson = gson.toJson(message,type);
        String ts = gson.fromJson(beanStringToJson, type);
        System.out.println(ts);
        try {
        	ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
        	ServletActionContext.getResponse().getWriter().write(beanStringToJson);
//        	ServletActionContext.getResponse().getWriter().write("我是j2ee");
        	System.out.println("写入成功"+beanStringToJson.toString());
        } catch (IOException e) {    
           e.printStackTrace();    
        }    
	}
	
	public String submit(){
		this.doValidation();
		
		String code = (String) ActionContext.getContext().getSession().get("code");
		
		code = code.toLowerCase();
		this.code = this.code.toLowerCase();
		
//		if (!this.code.equals(code)) {
//			super.addFieldError("uPerror", "验证码输入有误！");
//			return INPUT;
//		}
//		
		if(!super.getFieldErrors().isEmpty())
			return INPUT;
		
		user = UserService.findUser(username);
		if(user!=null && user.getActived() == 0){
			super.addFieldError("activeError", "该用户还未激活，请到邮箱激活");
			return INPUT;
		}
		if(user!=null && (MD5Util.MD5(password)).equals(user.getPassword())){
			type=user.getType();
			int i = Integer.valueOf(type)-1;
			ServletActionContext.getRequest().getSession().setAttribute(re[i],user.getUsername());
			ServletActionContext.getRequest().getSession().setAttribute(re[(i+1)%3],null);
			ServletActionContext.getRequest().getSession().setAttribute(re[(i+2)%3],null);
			ServletActionContext.getRequest().getSession().setAttribute("currentUser",user.getUsername());
			ServletActionContext.getRequest().getSession().setAttribute("currentUser1",user);
			if (user.getType().equals("1"))
				ServletActionContext.getRequest().getSession().setAttribute("student",StudentService.findStudent(user.getUsername()));
			if (user.getType().equals("2"))
				ServletActionContext.getRequest().getSession().setAttribute("teacher",TeacherService.findTeacher(user.getUsername()));
			
			return re[Integer.valueOf(type)-1];
		}
		super.addFieldError("uPerror", "用户名或密码错误");
		return INPUT;
	}
	
	public void doValidation(){
		if(StringUtils.isBlank(username)){
			super.addFieldError("username","用户名不能为空");
			return;
		}
		if(StringUtils.isBlank(password)){
			super.addFieldError("password","密码不能为空");
			return;
		}
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
