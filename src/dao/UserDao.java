package dao;

import model.Course;
import model.User;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dao.support.HibernateUtil;


public class UserDao {

	public User findUser(String username){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		User user = (User) session.createCriteria(User.class)
				.add(Restrictions.eq("username", username)).uniqueResult();
		
		return user;
	}
	
	public User findUserByVid(String vid){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		User user = (User) session.createCriteria(User.class)
				.add(Restrictions.eq("url",vid)).uniqueResult();
		return user;
	}
	
	public User findUserByVid2(String vid){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		User user = (User) session.createCriteria(User.class)
				.add(Restrictions.eq("url",vid)).uniqueResult();
		return user;
	}
	
	public User save(User user){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		
		return user;
	}
	
	public void delete(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}
	
	public void delete2(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}
}
