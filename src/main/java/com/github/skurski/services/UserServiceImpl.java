package com.github.skurski.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.skurski.dao.UserDao;
import com.github.skurski.domain.User;

public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public int insertRow(User user) {
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
	public int updateRow(User user) {
		return userDao.updateRow(user);
	}

	@Override
	public int deleteRow(int id) {
		return userDao.deleteRow(id);
	}
	
	@Override
	public boolean checkIfUserExistsByEmail(String email) {
		return userDao.checkIfUserExistsByEmail(email);
	}
	
	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

}
