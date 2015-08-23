package com.github.skurski.dao;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.skurski.domain.Travel;
import com.github.skurski.domain.User;

public class TravelDao implements ITravelDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public int insertRow(Travel travel) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(travel);
		tx.commit();
		Serializable id = session.getIdentifier(travel);
		session.close();
		return (Integer) id;	
	}
	
	@Override
	public Travel getRowById(int id) {
		Session session = sessionFactory.openSession();
		Travel travel = (Travel) session.load(Travel.class, id);
		return travel;
	}
	
	@Override
	public int updateRow(Travel travel) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
//		Query query = session.createQuery("update Travel t set t.name = :name, t.location = :location, "
//				+ "t.latitude = :latitude, t.longitude = :longitude, t.description = :description where t.id = :id");
//		query.setParameter("id", travel.getId());
//		query.setString("name", travel.getName());
//		query.setString("location", travel.getLocation());
//		query.setString("latitude", travel.getLatitude());
//		query.setString("longitude", travel.getLongitude());
//		query.setString("description", travel.getDescription());
//		System.out.println(travel.getDescription());
//		System.out.println(travel.getId());
//		query.executeUpdate();
		
		session.saveOrUpdate(travel);
		tx.commit();
		Serializable id = session.getIdentifier(travel);
		session.close();
		return (Integer) id;
	}

}
