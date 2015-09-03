package com.github.skurski.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.skurski.dao.UserDao;
import com.github.skurski.domain.Travel;
import com.github.skurski.domain.User;

public class UserService implements IService {
	
	@Autowired
	UserDao userDao;

	@Override
	@Transactional
	public int insertRow(Object user) {
		return userDao.insertRow(user);
	}

	@Override
	@Transactional
	public List<User> getList() {
		return userDao.getList();
	}
	
	@Override
	@Transactional
	public List<User> getListByRelatedObject(Object object, String objName) {
		return null;
	}

	@Override
	@Transactional
	public User getRowById(int id) {
		return userDao.getRowById(id);
	}

	@Override
	@Transactional
	public int updateRow(Object user) {
		return userDao.updateRow(user);
	}

	@Override
	@Transactional
	public int deleteRow(int id) {
		return userDao.deleteRow(id);
	}
	
	@Override
	@Transactional
	public boolean checkIfObjectExistByString(String column, String email) {
		return userDao.checkIfObjectExistByString(column, email);
	}
	
	@Override
	@Transactional
	public User getObjectByString(String column, String email) {
		return userDao.getObjectByString(column, email);
	}

}
