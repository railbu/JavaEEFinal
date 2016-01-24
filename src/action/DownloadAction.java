package action;

import java.io.InputStream;

import model.User;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import service.UserService;
import service.utils.MD5Util;
import action.support.Message;
import action.support.MessageType;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {

	private static final long serialVersionUID = 1823217579181896614L;

	private String filePath;
	private String fileName;
	private int price;
	
	private String iflogin = "false";

	private String password;
	private String username;
	private String type;
	private String code;
	private String message;
	private String[] re = { "stuUser", "teaUser", "visUser" };
	private User user = null;
	
	
	//result时候的下载
	public String down() {
		
		String user = (String) ActionContext.getContext().getSession()
					.get("currentUser");
			if (user == null) {
				return INPUT;
			}
			// 改为空，不显示登陆
			iflogin = null;
		
		return SUCCESS;
	}


	
	public String logins() {
		if (StringUtils.isBlank(getUsername())) {
			setMessage("Username cannot be null");
			return INPUT;
		}
		if (StringUtils.isBlank(getPassword())) {
			setMessage("Password cannot be null");
			return INPUT;
		}
		
		String code = (String) ActionContext.getContext().getSession()
				.get("code");
		code = code.toLowerCase();
		this.code = this.code.toLowerCase();

		 if (!this.code.equals(code)) {
			 setMessage("Validation number is wrong");
			 return INPUT;
		 }
		
		if (!super.getFieldErrors().isEmpty())
			return INPUT;

		setUser(UserService.findUser(getUsername()));
		
		if (getUser().getActived() == 0) {
			setMessage("This user is not actived");
			return INPUT;
		}
		
		
		if (getUser() != null && MD5Util.MD5(getPassword()).equals(getUser().getPassword())) {
			setType(getUser().getType());
			int i = Integer.valueOf(getType()) - 1;
			ServletActionContext.getRequest().getSession()
					.setAttribute(getRe()[i], getUser().getUsername());
			ServletActionContext.getRequest().getSession()
					.setAttribute(getRe()[(i + 1) % 3], null);
			ServletActionContext.getRequest().getSession()
					.setAttribute(getRe()[(i + 2) % 3], null);
			ServletActionContext.getRequest().getSession()
					.setAttribute("currentUser", getUser().getUsername());
			ServletActionContext.getRequest().getSession()
					.setAttribute("currentUser1", user);
			System.out.println("cnm"+user.getType());
			// 改为空，不显示登陆框
			iflogin = null;
			return INPUT;
		}
		setMessage("Username or password is wrong");
		return INPUT;
	}
	
	
	//点进去时候的下载
	public String downIn() {
		ActionContext.getContext().getSession().put("nameIndisk", filePath);
		ActionContext.getContext().getSession().put("content", "content.ppt");
		String user = (String) ActionContext.getContext().getSession()
					.get("currentUser");
			if (user == null) {
				return "login";
			}
			// 改为空，不显示登陆
			iflogin = null;
			
		return SUCCESS;
	}
	
	//点进去时候的登陆验证
	public String loginsIn() {
		
		if (StringUtils.isBlank(getUsername())) {
			setMessage("Username cannot be null");
			return "login";
		}
		if (StringUtils.isBlank(getPassword())) {
			setMessage("Password cannot be null");
			return "login";
		}
		
		String code = (String) ActionContext.getContext().getSession()
				.get("code");
		code = code.toLowerCase();
		this.code = this.code.toLowerCase();

		 if (!this.code.equals(code)) {
			 setMessage("Validation number is wrong");
			 return "login";
		 }
		
		if (!super.getFieldErrors().isEmpty())
			return "login";

		setUser(UserService.findUser(getUsername()));
		
		if (getUser().getActived() == 0) {
			setMessage("This user is not actived");
			return "login";
		}
		
		
		if (getUser() != null && MD5Util.MD5(getPassword()).equals(getUser().getPassword())) {
			setType(getUser().getType());
			int i = Integer.valueOf(getType()) - 1;
			ServletActionContext.getRequest().getSession()
					.setAttribute(getRe()[i], getUser().getUsername());
			ServletActionContext.getRequest().getSession()
					.setAttribute(getRe()[(i + 1) % 3], null);
			ServletActionContext.getRequest().getSession()
					.setAttribute(getRe()[(i + 2) % 3], null);
			ServletActionContext.getRequest().getSession()
					.setAttribute("currentUser", getUser().getUsername());
			ServletActionContext.getRequest().getSession()
					.setAttribute("currentUser1", user);
			System.out.println("cnm"+user.getType());
			// 改为空，不显示登陆框
			iflogin = null;
			return "login";
		}
		setMessage("Username or password is wrong");
		return "login";
	}
	
	public InputStream getDownloadFile() throws Exception {
		return ServletActionContext.getServletContext().getResourceAsStream(
				filePath);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getIflogin() {
		return iflogin;
	}

	public void setIflogin(String iflogin) {
		this.iflogin = iflogin;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getRe() {
		return re;
	}

	public void setRe(String[] re) {
		this.re = re;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
