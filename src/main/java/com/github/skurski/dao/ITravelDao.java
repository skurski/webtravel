package com.github.skurski.dao;

import com.github.skurski.domain.Travel;
import com.github.skurski.domain.User;

public interface ITravelDao {
	public int insertRow(Travel travel);
	
	public Travel getRowById(int id);
	
	public int updateRow(Travel travel);

}
