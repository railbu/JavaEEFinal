package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dao.support.HibernateUtil;
import model.CourseStu;

public class CourseStuDAO {
	
	private List<CourseStu> list = null; 
	public void insert(List<CourseStu> cslist){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		for(int i=0;i<cslist.size();i++){
			CourseStu cs = cslist.get(i);
			session.save(cs);
		}
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CourseStu> findAllIncid(Long cid){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		list= session.createCriteria(CourseStu.class).add(Restrictions.eq("cid", cid)).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<CourseStu> findAllInStuNo(String stuNo){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		list= session.createCriteria(CourseStu.class).add(Restrictions.eq("stuNo", stuNo)).list();
		return list;
	}
	
	public void delete(Long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.delete(session.load(CourseStu.class,id));
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}
	
}
