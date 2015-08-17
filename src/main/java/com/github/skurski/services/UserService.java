package com.github.skurski.services;

import java.util.List;

import com.github.skurski.domain.User;

public interface UserService {
	public int insertRow(User user);

	public List<User> getList();

	public User getRowById(int id);

	public int updateRow(User user);

	public int deleteRow(int id);

}
