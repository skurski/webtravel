package com.github.skurski.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.skurski.dao.TravelDao;
import com.github.skurski.domain.Travel;
import com.github.skurski.domain.User;

public class TravelService implements IService {
	
	@Autowired
	TravelDao travelDao;

	@Override
	@Transactional
	public int insertRow(Object travel) {
		return travelDao.insertRow(travel);
	}

	@Override
	@Transactional
	public List<Travel> getList() {
		return travelDao.getList();
	}
	
	@Override
	@Transactional
	public List<Travel> getListByRelatedObject(Object user, String objName) {
		return travelDao.getListByRelatedObject(user, objName);
	}

	@Override
	@Transactional
	public Travel getRowById(int id) {
		return travelDao.getRowById(id);
	}

	@Override
	@Transactional
	public int updateRow(Object travel) {
		return travelDao.updateRow(travel);
	}

	@Override
	@Transactional
	public int deleteRow(int id) {
		return travelDao.deleteRow(id);
	}
	
	@Override
	@Transactional
	public boolean checkIfObjectExistByString(String column, String email) {
		return travelDao.checkIfObjectExistByString(column, email);
	}
	
	@Override
	@Transactional
	public Travel getObjectByString(String column, String email) {
		return travelDao.getObjectByString(column, email);
	}

}
