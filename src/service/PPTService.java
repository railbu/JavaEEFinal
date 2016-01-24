package service;

import java.util.List;

import model.PPT;
import dao.PPTDAO;

public class PPTService {
	public static PPTDAO pptDAO = new PPTDAO();
	
	public static List<PPT> findAll(Long cid){
		return pptDAO.findAll(cid);
	}
	
	public static List<PPT> findAllPPTS(){
		return pptDAO.findAllPPTS();
	}
	
	public static PPT findPPT(Long id){
		return pptDAO.findPPT(id);
	}
	
	public static void delete(Long id){
		pptDAO.delete(id);
	}
	
	public static void save(PPT ppt){
		pptDAO.save(ppt);
	}
}
