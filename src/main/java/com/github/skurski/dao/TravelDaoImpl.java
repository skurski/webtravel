package com.github.skurski.dao;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.skurski.domain.Travel;

public class TravelDaoImpl implements Dao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public int insertRow(Object travel) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(travel);
		Serializable id = session.getIdentifier(travel);
		session.getTransaction().commit();
		return (Integer) id;	
	}

	@Override
	public List<Travel> getList() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Travel> travelList = session.createQuery("from Travel")
				.list();
		session.getTransaction().commit();
		return travelList;
	}
	
	public List<Travel> getListByRelatedObject(Object user, String objName) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Travel> travelList = session.createCriteria(Travel.class)
								.add(Restrictions.eq(objName, user)).list();
		session.getTransaction().commit();
		return travelList;
	}

	@Override
	public Travel getRowById(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Travel travel = (Travel) session.get(Travel.class, id);
		session.getTransaction().commit();
		return travel;
	}

	@Override
	public int updateRow(Object travel) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
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
		Serializable id = session.getIdentifier(travel);
		session.getTransaction().commit();
		return (Integer) id;
	}

	@Override
	public int deleteRow(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Travel travel = (Travel) session.load(Travel.class, id);
		session.delete(travel);
		Serializable ids = session.getIdentifier(travel);
		session.getTransaction().commit();
		return (Integer) ids;
	}
	
	@Override
	public boolean checkIfObjectExistByString(String column, String name) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
        Criteria criteria = sessionFactory.openSession().createCriteria(Travel.class);
        criteria.add(Restrictions.like(column, name));
        Travel travel =  (Travel) criteria.uniqueResult();
		session.getTransaction().commit();
		
		if(travel != null) return true;
		else return false;
	}
	
	
	@Override
	public Travel getObjectByString(String column, String name) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
        Criteria criteria = sessionFactory.openSession().createCriteria(Travel.class);
        criteria.add(Restrictions.like(column, name));
        Travel travel =  (Travel) criteria.uniqueResult();
		session.getTransaction().commit();
        return travel;
	}

}