package dao;

import model.Visitor;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dao.support.HibernateUtil;


public class VisitorDAO {

	public Visitor findVisitor(String username){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Visitor Visitor = (Visitor) session.createCriteria(Visitor.class)
				.add(Restrictions.eq("username", username)).uniqueResult();
		
		return Visitor;
	}
	
	public Visitor save(Visitor Visitor){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.save(Visitor);
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		return Visitor;
	}
}
