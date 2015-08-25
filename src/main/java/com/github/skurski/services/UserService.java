package com.github.skurski.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.skurski.dao.UserDao;
import com.github.skurski.domain.User;

public class UserService implements IService {
	
	@Autowired
	UserDao userDao;

	@Override
	public int insertRow(Object user) {
		return userDao.insertRow(user);
	}

	@Override
	public List<User> getList() {
		return userDao.getList();
	}

	@Override
	public User getRowById(int id) {
		return userDao.getRowById(id);
	}

	@Override
	public int updateRow(Object user) {
		return userDao.updateRow(user);
	}

	@Override
	public int deleteRow(int id) {
		return userDao.deleteRow(id);
	}
	
	@Override
	public boolean checkIfObjectExistByString(String column, String email) {
		return userDao.checkIfObjectExistByString(column, email);
	}
	
	@Override
	public User getObjectByString(String column, String email) {
		return userDao.getObjectByString(column, email);
	}

}
