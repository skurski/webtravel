package com.github.skurski.dao;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.skurski.domain.User;

public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public int insertRow(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(user);
		tx.commit();
		Serializable id = session.getIdentifier(user);
		session.close();
		return (Integer) id;
	}

	@Override
	public List<User> getList() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<User> userList = session.createQuery("from User")
				.list();
		session.close();
		return userList;
	}

	@Override
	public User getRowById(int id) {
		Session session = sessionFactory.openSession();
		User user = (User) session.load(User.class, id);
		return user;
	}

	@Override
	public int updateRow(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(user);
		tx.commit();
		Serializable id = session.getIdentifier(user);
		session.close();
		return (Integer) id;
	}

	@Override
	public int deleteRow(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user = (User) session.load(User.class, id);
		session.delete(user);
		tx.commit();
		Serializable ids = session.getIdentifier(user);
		session.close();
		return (Integer) ids;
	}
	
	@Override
	public boolean checkIfUserExistsByEmail(String email) {
//		Session session = sessionFactory.openSession();

        Criteria criteria = sessionFactory.openSession().createCriteria(User.class);
        criteria.add(Restrictions.like("email", email));
        User user =  (User) criteria.uniqueResult();
		
		if(user != null) return true;
		else return false;
	}
	
	
	@Override
	public User getUserByEmail(String email) {
        Criteria criteria = sessionFactory.openSession().createCriteria(User.class);
        criteria.add(Restrictions.like("email", email));
        return (User) criteria.uniqueResult();
	}

	
	/*
	Session sess = factory.openSession();
	 Transaction tx;
	 try {
	     tx = sess.beginTransaction();
	     //do some work
	     ...
	     tx.commit();
	 }
	 catch (Exception e) {
	     if (tx!=null) tx.rollback();
	     throw e;
	 }
	 finally {
	     sess.close();
	 }
	 */

}
