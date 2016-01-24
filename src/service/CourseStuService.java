package service;

import java.util.List;

import model.CourseStu;
import dao.CourseStuDAO;

public class CourseStuService {
	public static CourseStuDAO csDAO = new CourseStuDAO();
	
	public static void Insert(List<CourseStu> cslist){
		csDAO.insert(cslist);
	}
	
	public static List<CourseStu> findAllInCid(Long cid){
		return csDAO.findAllIncid(cid);
	}
	
	public static List<CourseStu> findAllInStuNo(String stuNo){
		return csDAO.findAllInStuNo(stuNo);
	}
	
	public static void delete(Long id){
		csDAO.delete(id);
	}
}
