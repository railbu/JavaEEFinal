package action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import service.utils.MySessionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginScanAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private String username;
	private String sessionId;
	MySessionContext msc = MySessionContext.getInstance();
	
	public String loginscan(){
		HttpSession session = msc.getSession(sessionId);
		if(session != null){
			session.setAttribute("CurrentUser",username);
		}
		return null;
	}

	public String loginscanweb(){
		HttpServletResponse response = ServletActionContext.getResponse(); 

		response.setContentType("text/plain;charset=UTF-8");

		String state = (String) ActionContext.getContext().getSession().get("state");

		try { 
		     response.getWriter().write(state); 
		} catch (IOException e) { 
		e.printStackTrace(); 
		}
		return null;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
