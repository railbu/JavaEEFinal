package service;

import java.util.List;

import model.StuCourse;
import dao.StuCourseDAO;

public class StuCourseService {
	private StuCourseDAO stuCourseDAO = new StuCourseDAO();
	
	public void save(StuCourse stuCourse){
		this.stuCourseDAO.save(stuCourse);
	}

	public StuCourse getStuCourse(Long id){
		return this.stuCourseDAO.getStuCourse(id);
	}
	
	public StuCourse getStuCourseByOther(String sname, String no, String week) {
		return this.stuCourseDAO.getStuCourseByOther(sname, no, week);
	}
	
	public List<StuCourse> findAll(String sname){
		return this.stuCourseDAO.findAll(sname);
	}

	public void delete(Long id) {
		this.stuCourseDAO.delete(id);
	}
}
