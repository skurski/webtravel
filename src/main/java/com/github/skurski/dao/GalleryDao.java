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
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(gallery);
		tx.commit();
		Serializable id = session.getIdentifier(gallery);
		session.close();
		return (Integer) id;	
	}

	@Override
	public List<Gallery> getList() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Gallery> galleryList = session.createQuery("from Gallery")
				.list();
		session.close();
		return galleryList;
	}

	@Override
	public Gallery getRowById(int id) {
		Session session = sessionFactory.openSession();
		Gallery gallery = (Gallery) session.load(Gallery.class, id);
		return gallery;
	}

	@Override
	public int updateRow(Object gallery) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(gallery);
		tx.commit();
		Serializable id = session.getIdentifier(gallery);
		session.close();
		return (Integer) id;
	}

	@Override
	public int deleteRow(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Gallery gallery = (Gallery) session.load(Gallery.class, id);
		session.delete(gallery);
		tx.commit();
		Serializable ids = session.getIdentifier(gallery);
		session.close();
		return (Integer) ids;
	}
	
	@Override
	public boolean checkIfObjectExistByString(String column, String name) {
        Criteria criteria = sessionFactory.openSession().createCriteria(Gallery.class);
        criteria.add(Restrictions.like(column, name));
        Gallery travel =  (Gallery) criteria.uniqueResult();
		
		if(travel != null) return true;
		else return false;
	}
	
	
	@Override
	public Gallery getObjectByString(String column, String name) {
        Criteria criteria = sessionFactory.openSession().createCriteria(Gallery.class);
        criteria.add(Restrictions.like(column, name));
        return (Gallery) criteria.uniqueResult();
	}

}