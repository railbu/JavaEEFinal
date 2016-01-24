package service;

import model.User;
import dao.UserDao;


public class UserService {

	private static UserDao userDao = new UserDao();
	
	public static void register(User user){
		userDao.save(user);
	}	
	/**
	 * 登录校验
	 */
	public static User checkUser(String username, String password){
		User user = userDao.findUser(username);
		if(user == null || !user.getPassword().equals(password))
			throw new LoginFailureException("用户名或密码错误");
		else
			return user;
	}
	
	public static User findUserByVid(String vid){
		return userDao.findUserByVid(vid);
	}
	
	public static User findUserByVid2(String vid){
		return userDao.findUserByVid2(vid);
	}
	
	public static User findUser(String username){
		return userDao.findUser(username);
	}
	public static void delete(User u){
		userDao.delete(u);
	}
	public static void delete2(User u){
		userDao.delete2(u);
	}
}
