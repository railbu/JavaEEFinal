package service;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.Teacher;
import dao.TeacherDAO;


public class TeacherService {

	private static TeacherDAO teacherDAO = new TeacherDAO();
	
	public static void register(Teacher teacher){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
		String regTime = dateFormat.format(now); 
		teacher.setRegTime(regTime);
		teacherDAO.save(teacher);
	}	
	/**
	 * 登录校验
	 */
	public static Teacher checkTeacher(String username, String password){
		Teacher Teacher = teacherDAO.findTeacher(username);
		if(Teacher == null || !Teacher.getPassword().equals(password))
			throw new LoginFailureException("用户名或密码错误");
		else
			return Teacher;
	}
	
	public static void save(Teacher teacher) {
		teacherDAO.save(teacher);
	}
	
	public static Teacher findTeacher(String username){
		return teacherDAO.findTeacher(username);
	}
}
