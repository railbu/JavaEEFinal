package service;

import java.util.List;

import model.StuPpt;
import dao.StuPptDAO;


public class StuPptService {
	private StuPptDAO stupptDAO = new StuPptDAO();
	
	public void save(StuPpt stuppt){
		this.stupptDAO.save(stuppt);
	}

	public StuPpt getStuPpt(Long id){
		return this.stupptDAO.getPpt(id);
	}
	
	public List<StuPpt> findAll(){
		return this.stupptDAO.findAll();
	}

	public void delete(Long id) {
		this.stupptDAO.delete(id);
	}
}
