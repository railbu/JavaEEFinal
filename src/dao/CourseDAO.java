package dao;

import java.util.List;

import model.Course;
import model.User;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dao.support.HibernateUtil;

public class CourseDAO {

	private List<Course> list = null;

	@SuppressWarnings("unchecked")
	public List<Course> findAll(String tName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		list = session.createCriteria(Course.class)
				.add(Restrictions.eq("TName", tName)).list();
		return list;
	}
	
	public Course save(Course c) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		return c;
	}

	public void setStuCount(Long id, int a) {
		Course c = getCourse(id);
		c.setStuCount(a);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.saveOrUpdate(c);
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}

	public void setPPTCount(Long id, int a) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

			Course c = getCourse(id);
			c.setPptCount(a);
			session.beginTransaction();
			session.saveOrUpdate(c);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
	}

	public int getStuCount(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Course c = (Course) session.get(Course.class, id);
		return c.getStuCount();
	}

	public Course getCourse(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (Course) session.get(Course.class, id);
	}
	
	public Course getCourseFromCname(String cName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Course c = (Course) session.createCriteria(Course.class)
				.add(Restrictions.eq("name",cName)).uniqueResult();
		return c;
	}
	
	public int getPPTCount(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Course c = (Course) session.get(Course.class, id);
		return c.getPptCount();
	}

	public void setHworkCount(Long id, int a) {
		Course c = getCourse(id);
		c.setHworkCount(a);

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(c);
		session.getTransaction().commit();
	}

	public int getHworkCount(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Course c = (Course) session.get(Course.class, id);
		return c.getHworkCount();
	}

	public void delete(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.delete(session.load(Course.class, id));
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}
}
