package dao;

import java.util.List;

import model.TSMessage;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dao.support.HibernateUtil;

public class TSMessageDAO {
	
	@SuppressWarnings("unchecked")
	public List<TSMessage> findAll(String stuNo){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return session.createCriteria(TSMessage.class).add(Restrictions.eq("stuNo", stuNo)).list();
	}
	
	public void save(TSMessage m){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.save(m);
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}
	
	public void delete(Long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.delete(session.load(TSMessage.class,id));
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}
}
