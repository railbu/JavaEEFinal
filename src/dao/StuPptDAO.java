package dao;

import java.util.List;

import model.StuPpt;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import dao.support.HibernateUtil;

public class StuPptDAO {
	@SuppressWarnings("unchecked")
	public List<StuPpt> findAll(){
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createCriteria(StuPpt.class)
				.addOrder(Order.asc("id")).list();
	}

	public StuPpt getPpt(Long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		return (StuPpt) session.get(StuPpt.class, id);
	}
	
	public void save(StuPpt ppt) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.saveOrUpdate(ppt);
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}

	public void delete(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.delete(session.load(StuPpt.class,id));
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}
}
