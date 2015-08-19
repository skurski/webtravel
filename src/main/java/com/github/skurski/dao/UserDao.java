package com.github.skurski.dao;

import java.util.List;

import com.github.skurski.domain.User;

public interface UserDao {
	public int insertRow(User user);

	public List<User> getList();

	public User getRowById(int id);

	public int updateRow(User user);

	public int deleteRow(int id);
	
	public boolean checkIfUserExistsByEmail(String email);
	
	public User getUserByEmail(String email);

}
