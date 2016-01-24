package dao;

import java.util.List;

import model.Homework;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dao.support.HibernateUtil;

public class HomeworkDAO {
	
	public void save(Homework h){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.save(h);
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Homework> findAll(Long cid){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return session.createCriteria(Homework.class).add(Restrictions.eq("cid", cid))
				.list();
	}
	
	public Homework getHomework(Long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (Homework) session.get(Homework.class, id);
	}
	
	public void delete(Long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.delete(session.load(Homework.class,id));
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}
}
