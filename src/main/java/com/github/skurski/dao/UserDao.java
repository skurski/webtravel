package com.github.skurski.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.skurski.domain.User;

public class UserDao implements IDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int insertRow(Object user) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(user);
		Serializable id = session.getIdentifier(user);
		session.getTransaction().commit();
		return (Integer) id;
	}

	@Override
	public List<User> getList() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<User> userList = session.createQuery("from User")
				.list();
		session.getTransaction().commit();
		return userList;
	}

	@Override
	public User getRowById(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		User user = (User) session.load(User.class, id);
		session.getTransaction().commit();
		return user;
	}

	@Override
	public int updateRow(Object user) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(user);
		Serializable id = session.getIdentifier(user);
		session.getTransaction().commit();
		return (Integer) id;
	}

	@Override
	public int deleteRow(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		User user = (User) session.load(User.class, id);
		session.delete(user);
		Serializable ids = session.getIdentifier(user);
		session.getTransaction().commit();
		return (Integer) ids;
	}
	
	@Override
	public boolean checkIfObjectExistByString(String column, String name) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like(column, name));
        User user =  (User) criteria.uniqueResult();
		session.getTransaction().commit();
		
		if(user != null) return true;
		else return false;
	}
	
	
	@Override
	public User getObjectByString(String column, String name) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like(column, name));
        User user =  (User) criteria.uniqueResult();
		session.getTransaction().commit();
        return user;
	}

}
