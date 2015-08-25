package com.github.skurski.dao;

import java.util.List;

public interface IDao {
	public int insertRow(Object object);

	public List<? extends Object> getList();

	public <T> T getRowById(int id);

	public int updateRow(Object object);

	public int deleteRow(int id);
	
	public boolean checkIfObjectExistByString(String column, String name);
	
	public <T> T getObjectByString(String column, String name);
}
