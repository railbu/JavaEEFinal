package service;

import java.util.List;

import model.Course;
import dao.CourseDAO;

public class CourseService {
	public static CourseDAO cd = new CourseDAO();
	
	public static List<Course> findAll(String tName){
		return cd.findAll(tName);
	}
	
	public static void save(Course c){
		cd.save(c);
	}
	
	public static Course getCourseFromCname(String cName) {
		return cd.getCourseFromCname(cName);
	}
	
	public static void setStuCount(Long id,int a){
		cd.setStuCount(id, a);
	}
	
	public static void setPPTCount(Long id,int a){
		cd.setPPTCount(id, a);
	}
	
	public static int getStuCount(Long id){
		return cd.getStuCount(id);
	}
	
	public static Course getCourse(Long id){
		return cd.getCourse(id);
	}
	
	public static int getPPTCount(Long id){
		return cd.getPPTCount(id);
	}
	
	public static void addStuCount(Long id,int count){
		int temp = getStuCount(id);
		setStuCount(id, temp+count);
	}
	
	public static void addPPTCount(Long id,int count){
		int temp = getPPTCount(id);
		setPPTCount(id, temp+count);
	}
	
	public static void delete(Long id){
		cd.delete(id);
	}
	
	public static void addHworkCount(Long id,int count){
		cd.setHworkCount(id, cd.getHworkCount(id)+count);
	}
}
