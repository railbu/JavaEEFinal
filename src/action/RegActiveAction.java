package action;

import model.User;

import org.apache.struts2.ServletActionContext;

import service.UserService;
import action.support.Message;
import action.support.MessageType;

import com.opensymphony.xwork2.ActionSupport;

public class RegActiveAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String result;
	private Message message;
	
	public String valid(){
		try{
		String vid = ServletActionContext.getRequest().getParameter("vid");
		
		User user = UserService.findUserByVid(vid);
		if(user == null || user.getActived()==1){
			message = new Message("该链接已过期",null,MessageType.warning);
			return "guoqi";
		}else{
			message = new Message("激活成功",null,MessageType.success);
			user.setActived(1);
			UserService.register(user);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
