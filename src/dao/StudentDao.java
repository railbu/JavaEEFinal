package dao;

import model.Student;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dao.support.HibernateUtil;


public class StudentDao {

	public Student findStudent(String username){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Student Student = null;
		try{
		Student = (Student) session.createCriteria(Student.class)
				.add(Restrictions.eq("username",username)).uniqueResult();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		return Student;
		
	}
	
	public Student findStudentAcStuNo(String stuNo){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Student Student = (Student) session.createCriteria(Student.class)
				.add(Restrictions.eq("stuNo",stuNo)).uniqueResult();
		return Student;
	}
	
	public void save(Student Student){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.saveOrUpdate(Student);
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}
	
	public void delete(Student student){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.delete(student);
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}
}
