package action;

import model.User;
import model.Visitor;
import service.UserService;
import service.VisitorService;
import action.support.Message;
import action.support.MessageType;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class VRegisterAction extends ActionSupport implements Preparable,ModelDriven<Visitor>{

	private static final long serialVersionUID = -7288870851052862957L;
	private Visitor visitor;
	private String passwordAgain;
	private Message message;
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
			try{
				User user = new User();
				user.setUsername(visitor.getUsername());
				user.setPassword(visitor.getPassword());
				user.setType("3");
				UserService.register(user);
				
				VisitorService.register(visitor);
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
			this.message = new Message("注册成功","/index.jsp",MessageType.success);
			return SUCCESS;
		}
	}

	private void validateRegisterInfo() {
		if(StringUtils.isBlank(this.visitor.getUsername())){
			super.addFieldError("username", "用户名不能为空");
			return;
		}
		
		if(UserService.findUser(visitor.getUsername())!=null){
			super.addFieldError("usernameRepeat", "该用户名已被使用");
			return;	
		}
		
		if(StringUtils.isBlank(this.visitor.getPassword())){
			super.addFieldError("password", "密码不能为空");
			return;
		}else if(!this.visitor.getPassword().equals(passwordAgain)){
			super.addFieldError("passwordAgain", "两次输入密码不一致");
			return;
		}
		
		if(StringUtils.isBlank(this.visitor.getEmail())){
			super.addFieldError("email", "Email不能为空");
			return;
		}
		
	}
	
	@Override
	public void prepare() throws java.lang.Exception{
		this.visitor = new Visitor();
	}

	@Override
	public Visitor getModel() {
		return this.visitor;
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

}
