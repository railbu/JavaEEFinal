package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import service.StudentService;
import service.utils.DivideWord;

import com.opensymphony.xwork2.ActionContext;

import dao.support.HibernateUtil;
import model.PPT;
import model.SResult;
import model.Student;


public class SearchDAO {
	private HashMap<String, Integer> prep = new HashMap<String, Integer>();
	private HashMap<PPT, Integer> pptAdded = new HashMap<PPT, Integer>();

	public SearchDAO() {
		prep.put("to", 1);
		prep.put("at", 1);
		prep.put("for", 1);
		prep.put("in", 1);
		prep.put("on", 1);
		prep.put("to", 1);
		prep.put("by", 1);
		prep.put("before", 1);
		prep.put("after", 1);
		prep.put("with", 1);
		prep.put("about", 1);
	}

	@SuppressWarnings("unchecked")
	public List<SResult> search(String input) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<SResult> sr = new ArrayList<SResult>();

		// 先处理输入的字符串
		List<String> wlist = null;
		try {
			wlist = DivideWord.divide(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("庖丁没接好");
		}
//		String[] words = input.split(" ");
//		for (int i = 0; i < words.length; i++) {
//			if (prep.get(words[i]) == null) {
//				if (words[i].length() > 7) {
//					words[i] = words[i].substring(0, words[i].length() - 3);
//				}
//				else if (words[i].length() > 5) {
//					words[i] = words[i].substring(0, words[i].length() - 2);
//				}
//				else if (words[i].length() > 4) {
//					words[i] = words[i].substring(0, words[i].length() - 1);
//				}
//				wlist.add(words[i]);
//			}
//		}
		for (int i = 0; i < wlist.size(); i++) {
			String hql = "from PPT as ppt where lower(ppt.name) like :word";

			Query q = session.createQuery(hql);
			q.setParameter("word", "%" + wlist.get(i).toLowerCase() + "%");
			List<PPT> plist = q.list();
			for (int j = 0; j < plist.size(); j++) {
				SResult s = new SResult();
				PPT ppt = plist.get(j);
				if (pptAdded.get(ppt) == null) {
					s.setPptId(ppt.getId());
					s.setContent(ppt.getName());
					s.setCreTime(ppt.getCreTime());
					s.setPrice(String.valueOf(ppt.getPrice()));
					s.setClickCount(String.valueOf(ppt.getClickCount()));
					s.setcName(ppt.getCourse().getName());
					s.settName(ppt.getCourse().getTName());
					s.setNameInDisk(ppt.getNameInDisk());
					String username = (String)ActionContext.getContext().getSession().get("currentUser");
					if (username != null && !username.equals("")){
						Student student = StudentService.findStudent(username);
							//学生的收藏的pptlist
						if (student != null){
							List<PPT> ppts = new ArrayList<PPT>(student.getPpts());
							for (PPT p : ppts) {
								if (p.equals(ppt)) {
									s.setType(1);
									break;
								}else{
									s.setType(0);
								}
							}
						}
					}
					sr.add(s);
					pptAdded.put(ppt, 1);
				}
			}
		}
		return sr;
	}
}
