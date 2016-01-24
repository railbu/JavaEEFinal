package dao;

import java.util.List;

import model.Course;
import model.PPT;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import dao.support.HibernateUtil;

public class PPTDAO {
	private List<PPT> list=null;
	
	@SuppressWarnings("unchecked")
	public List<PPT> findAll(Long cid){
		Course tc = new Course();
		tc.setId(cid);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		list = session.createCriteria(PPT.class).add(Restrictions.eq("course", tc)).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<PPT> findAllPPTS(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		list =  session.createCriteria(PPT.class).addOrder(Order.desc("creTime")).setMaxResults(3).list();
		return list;
	}
	
	public PPT findPPT(Long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		PPT ppt = (PPT) session.createCriteria(PPT.class)
				.add(Restrictions.eq("id",id)).uniqueResult();
		return ppt;
	}
	
	public void delete(Long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.delete(session.load(PPT.class,id));
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}
	
	public void save(PPT ppt){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.save(ppt);
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}
}
