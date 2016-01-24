package dao;

import java.util.List;

import model.StuCourse;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dao.support.HibernateUtil;

public class StuCourseDAO {
	@SuppressWarnings("unchecked")
	public List<StuCourse> findAll(String sname){
		List<StuCourse> list = HibernateUtil.getSessionFactory().getCurrentSession()
				.createCriteria(StuCourse.class)
				.add(Restrictions.eq("sname",sname))
				.list();
		return list;
	}

	public StuCourse getStuCourse(Long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		return (StuCourse) session.get(StuCourse.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public StuCourse getStuCourseByOther(String sname, String no, String week) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		List<StuCourse> l = session.createCriteria(StuCourse.class).add(Restrictions.eq("sname", sname)).list();
		
		for (StuCourse sc : l)
		{
			if (sc.getWeek().equals(week) && sc.getNo().equals(no))
			{
				return sc;
			}
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public void save(StuCourse stucourse) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		List<StuCourse> l = session.createCriteria(StuCourse.class).add(Restrictions.eq("sname",stucourse.getSname())).list();
		
		for (StuCourse sc : l)
		{
			if (sc.getWeek().equals(stucourse.getWeek()) && sc.getNo().equals(stucourse.getNo()))
			{
				StuCourseDAO.this.delete(sc.getId());
			}
		}
		try{
		session.beginTransaction();
		session.saveOrUpdate(stucourse);
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}

	public void delete(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.delete(session.load(StuCourse.class,id));
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}
}
