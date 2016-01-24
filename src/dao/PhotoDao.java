package dao;

import model.Photo;

import org.hibernate.Session;

import dao.support.HibernateUtil;

public class PhotoDao {
	public Photo getPhoto(String photoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (Photo) session.get(Photo.class, photoId);
	}
}
