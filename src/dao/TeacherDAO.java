package dao;

import model.Teacher;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dao.support.HibernateUtil;


public class TeacherDAO {

	public Teacher findTeacher(String username){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Teacher Teacher = (Teacher) session.createCriteria(Teacher.class)
				.add(Restrictions.eq("username", username)).uniqueResult();
		
		return Teacher;
	}
	
	public void save(Teacher Teacher){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.save(Teacher);
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}
}
