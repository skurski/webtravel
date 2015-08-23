package com.github.skurski.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.skurski.dao.TravelDao;
import com.github.skurski.domain.Travel;

public class TravelService implements ITravelService {
	
	@Autowired
	TravelDao travelDao;

	@Override
	public int insertRow(Travel travel) {
		return travelDao.insertRow(travel);
	}
	
	public Travel getRowById(int id) {
		return travelDao.getRowById(id);
	}
	
	public int updateRow(Travel travel) {
		return travelDao.updateRow(travel);
	}

}
