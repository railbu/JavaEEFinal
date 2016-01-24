package service;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.Visitor;
import dao.VisitorDAO;


public class VisitorService {

	private static VisitorDAO visitorDAO = new VisitorDAO();
	
	public static void register(Visitor visitor){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
		String regTime = dateFormat.format(now); 
		visitor.setRegTime(regTime);
		visitorDAO.save(visitor);
	}	
	/**
	 * 登录校验
	 */
	public static Visitor checkVisitor(String username, String password){
		Visitor Visitor = visitorDAO.findVisitor(username);
		if(Visitor == null || !Visitor.getPassword().equals(password))
			throw new LoginFailureException("用户名或密码错误");
		else
			return Visitor;
	}
	
	public static Visitor findVisitor(String username){
		return visitorDAO.findVisitor(username);
	}
}
