package service;

import java.util.List;

import dao.SearchDAO;
import model.SResult;

public class SearchService {
	public static List<SResult> search(String input){
		return new SearchDAO().search(input);
	}
}
