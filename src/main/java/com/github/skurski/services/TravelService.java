package com.github.skurski.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.skurski.dao.TravelDao;
import com.github.skurski.dao.UserDao;
import com.github.skurski.domain.Travel;
import com.github.skurski.domain.User;

public class TravelService implements IService {
	
	@Autowired
	TravelDao travelDao;

	@Override
	public int insertRow(Object travel) {
		return travelDao.insertRow(travel);
	}

	@Override
	public List<Travel> getList() {
		return travelDao.getList();
	}

	@Override
	public Travel getRowById(int id) {
		return travelDao.getRowById(id);
	}

	@Override
	public int updateRow(Object travel) {
		return travelDao.updateRow(travel);
	}

	@Override
	public int deleteRow(int id) {
		return travelDao.deleteRow(id);
	}
	
	@Override
	public boolean checkIfObjectExistByString(String column, String email) {
		return travelDao.checkIfObjectExistByString(column, email);
	}
	
	@Override
	public Travel getObjectByString(String column, String email) {
		return travelDao.getObjectByString(column, email);
	}

}
