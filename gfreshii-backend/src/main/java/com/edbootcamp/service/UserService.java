package com.edbootcamp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edbootcamp.dao.UserDao;
import com.edbootcamp.entity.UserImpl;

@Service
public class UserService {

	@Autowired
	private UserDao dao;
	
	public boolean isUserExist(UserImpl user) {
		return dao.isUserExist(user);
	}

	public UserImpl saveUser(UserImpl user) {
		return dao.saveUser(user);
	}

	public Boolean deleteUser(UserImpl user) {
		return dao.deleteUser(user);
	}

	public UserImpl findById(Long id) {
		return dao.findById(id);
	}

	public List<UserImpl> getAllUsers() {
		return dao.getAllUsers();
	}

	public UserImpl userByUsername(String userName) {
		return dao.userByUsername(userName);
	}

	public UserImpl loginUser(UserImpl user) {
		  return dao.loginUser(user);
		 }

	public UserImpl updateUser(UserImpl user) {
		return dao.updateUser(user);
	}
}
