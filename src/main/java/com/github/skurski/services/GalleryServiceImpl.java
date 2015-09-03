package com.github.skurski.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.skurski.dao.GalleryDaoImpl;
import com.github.skurski.domain.Gallery;

public class GalleryServiceImpl implements Service {
	
	@Autowired
	GalleryDaoImpl galleryDao;

	@Override
	@Transactional
	public int insertRow(Object travel) {
		return galleryDao.insertRow(travel);
	}

	@Override
	@Transactional
	public List<Gallery> getList() {
		return galleryDao.getList();
	}
	
	@Override
	@Transactional
	public List<Gallery> getListByRelatedObject(Object travel, String objName) {
		return galleryDao.getListByRelatedObject(travel, objName);
	}

	@Override
	@Transactional
	public Gallery getRowById(int id) {
		return galleryDao.getRowById(id);
	}

	@Override
	@Transactional
	public int updateRow(Object travel) {
		return galleryDao.updateRow(travel);
	}

	@Override
	@Transactional
	public int deleteRow(int id) {
		return galleryDao.deleteRow(id);
	}
	
	@Override
	@Transactional
	public boolean checkIfObjectExistByString(String column, String path) {
		return galleryDao.checkIfObjectExistByString(column, path);
	}
	
	@Override
	@Transactional
	public Gallery getObjectByString(String column, String path) {
		return galleryDao.getObjectByString(column, path);
	}

}
