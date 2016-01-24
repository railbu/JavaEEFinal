package service;

import java.util.List;

import model.TSMessage;
import dao.TSMessageDAO;

public class TSMessageService {
	private static TSMessageDAO md = new TSMessageDAO();
	public static List<TSMessage> findAll(String stuNo){
		return md.findAll(stuNo);
	}
	
	public static void save(TSMessage m){
		md.save(m);
	}
	
	public static void delete(Long id){
		md.delete(id);
	}
}
