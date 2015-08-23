package com.github.skurski.services;

import com.github.skurski.domain.Travel;
import com.github.skurski.domain.User;

public interface ITravelService {
	public int insertRow(Travel travel);
	
	public Travel getRowById(int id);
	
	public int updateRow(Travel travel);

}
