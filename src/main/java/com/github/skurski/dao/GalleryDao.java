package com.github.skurski.dao;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.skurski.domain.Gallery;
import com.github.skurski.domain.Travel;
import com.github.skurski.domain.User;

public class GalleryDao implements IDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public int insertRow(Object gallery) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(gallery);
		Serializable id = session.getIdentifier(gallery);
		session.getTransaction().commit();
		return (Integer) id;	
	}

	@Override
	public List<Gallery> getList() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Gallery> galleryList = session.createQuery("from Gallery")
				.list();
		session.getTransaction().commit();
		return galleryList;
	}
	
	@Transactional
	public List<Gallery> getListByRelatedObject(Object travel, String objName) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Gallery> galleryList = session.createCriteria(Gallery.class)
								.add(Restrictions.eq(objName, travel)).list();
		session.getTransaction().commit();
		return galleryList;
	}

	@Override
	public Gallery getRowById(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Gallery gallery = (Gallery) session.load(Gallery.class, id);
		session.getTransaction().commit();
		return gallery;
	}

	@Override
	public int updateRow(Object gallery) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(gallery);
		Serializable id = session.getIdentifier(gallery);
		session.getTransaction().commit();
		return (Integer) id;
	}

	@Override
	public int deleteRow(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Gallery gallery = (Gallery) session.load(Gallery.class, id);
		session.delete(gallery);
		Serializable ids = session.getIdentifier(gallery);
		session.getTransaction().commit();
		return (Integer) ids;
	}
	
	@Override
	public boolean checkIfObjectExistByString(String column, String name) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
        Criteria criteria = session.createCriteria(Gallery.class);
        criteria.add(Restrictions.like(column, name));
        Gallery travel =  (Gallery) criteria.uniqueResult();
		session.getTransaction().commit();
		
		if(travel != null) return true;
		else return false;
	}
	
	
	@Override
	public Gallery getObjectByString(String column, String name) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
        Criteria criteria = session.createCriteria(Gallery.class);
        criteria.add(Restrictions.like(column, name));
        Gallery gallery = (Gallery) criteria.uniqueResult();
		session.getTransaction().commit();
        return gallery;
	}

}