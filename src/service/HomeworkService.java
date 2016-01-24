package service;

import java.util.List;

import model.Homework;
import dao.HomeworkDAO;

public class HomeworkService {
	private static HomeworkDAO hDao = new HomeworkDAO();
	
	public static void save(Homework h){
		hDao.save(h);
		return;
	}
	
	public static List<Homework> findAll(Long cid){
		return hDao.findAll(cid);
	}
	
	public static Homework getHomework(Long id){
		return hDao.getHomework(id);
	}
	
	public static void delete(Long id){
		hDao.delete(id);
	}
}
