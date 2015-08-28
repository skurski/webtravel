package com.github.skurski.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.skurski.dao.GalleryDao;
import com.github.skurski.domain.Gallery;

public class GalleryService implements IService {
	
	@Autowired
	GalleryDao galleryDao;

	@Override
	public int insertRow(Object travel) {
		return galleryDao.insertRow(travel);
	}

	@Override
	public List<Gallery> getList() {
		return galleryDao.getList();
	}

	@Override
	public Gallery getRowById(int id) {
		return galleryDao.getRowById(id);
	}

	@Override
	public int updateRow(Object travel) {
		return galleryDao.updateRow(travel);
	}

	@Override
	public int deleteRow(int id) {
		return galleryDao.deleteRow(id);
	}
	
	@Override
	public boolean checkIfObjectExistByString(String column, String path) {
		return galleryDao.checkIfObjectExistByString(column, path);
	}
	
	@Override
	public Gallery getObjectByString(String column, String path) {
		return galleryDao.getObjectByString(column, path);
	}

}
