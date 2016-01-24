package service;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.Student;
import dao.PPTDAO;
import dao.StudentDao;


public class StudentService {

	private static StudentDao studentDao = new StudentDao();
	private static PPTDAO pptDao = new PPTDAO();
	
	public static void register(Student student){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
		String regTime = dateFormat.format(now); 
		student.setRegTime(regTime);
		studentDao.save(student);
	}
	/**
	 * 登录校验
	 */
	public static Student checkStudent(String username, String password){
		Student Student = studentDao.findStudent(username);
		if(Student == null || !Student.getPassword().equals(password))
			throw new LoginFailureException("用户名或密码错误");
		else
			return Student;
	}
	
	public static Student findStudent(String username){
		return studentDao.findStudent(username);
	}
	
	public static Student findStudentAcStuNo(String stuNo){
		return studentDao.findStudentAcStuNo(stuNo);
	}
	
	public static void save(Student student) {
		studentDao.save(student);
	}
	
	public static void delete(Student s){
		studentDao.delete(s);
	}
	
	public static void followPpt(String username, Long pptId) {
		Student student = studentDao.findStudent(username);
		student.addPpt(pptDao.findPPT(pptId));
		studentDao.save(student);
	}
	
	public static void cancelFollowPpt(String username, Long pptId) {
		Student student = studentDao.findStudent(username);
		student.getPpts().remove(pptDao.findPPT(pptId));
		studentDao.save(student);
	}
	
}
