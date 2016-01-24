package service;

import model.Photo;
import dao.PhotoDao;

public class PhotoService {
	private PhotoDao photoDao = new PhotoDao();
	
	public Photo getPhoto(String photoId){
		return photoDao.getPhoto(photoId);
	}
}
