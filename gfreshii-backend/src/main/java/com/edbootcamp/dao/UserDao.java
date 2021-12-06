package com.edbootcamp.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.edbootcamp.entity.UserImpl;

@Repository
@EnableTransactionManagement
@Transactional
public class UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Transactional
	public boolean isUserExist(UserImpl user) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserImpl where username = :username").setParameter("username", user.getUserName());
		UserImpl result = (UserImpl) query.uniqueResult();
		if(result==null) {
			return false;
		}
		return true;
	}
	@Transactional
	public UserImpl saveUser(UserImpl user) {
		
		Long id = (Long) hibernateTemplate.save(user);
		user.setId(id);
		return user;
	}

	@Transactional
	public Boolean deleteUser(UserImpl user) {
		UserImpl userImpl = findById(user.getId());
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("DELETE FROM UserImpl u WHERE u.id = :id");
		query.setParameter("id", userImpl.getId());
		int rowDeleted = query.executeUpdate();
		if(rowDeleted==0) {
			return false;
		}
		return true;
	}

	@Transactional
	public UserImpl findById(Long id) {
		return hibernateTemplate.get(UserImpl.class, id);
	}

	@Transactional
	public UserImpl userById(Long id) {
		return findById(id);
	}

	@Transactional
	public List<UserImpl> getAllUsers() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from UserImpl");
		Hibernate.initialize(query.list());
		List<UserImpl> list = query.list();
		System.out.println(list);
		return list;
	}
	public UserImpl userByUsername(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserImpl WHERE userName = :userName");
		query.setParameter("userName",userName);
		UserImpl userImpl = (UserImpl) query.uniqueResult();
		System.out.println(userImpl);
		return userImpl;
	}
	
	public UserImpl loginUser(UserImpl user) {
		  // TODO Login
		  Session session = sessionFactory.getCurrentSession();

		  Query query = session.createQuery("from UserImpl where userName =:userName and password =:password");
		  query.setParameter("userName", user.getUserName());
		  query.setParameter("password", user.getPassword());
		  user = (UserImpl) query.uniqueResult();
		  System.out.println(user);
		  return user;
		  
		 }
}
