package com.aptech.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aptech.dao.UserDAO;
import com.aptech.model.User;

@Service("userService")
public class UserService {
	@Autowired
	UserDAO userDAO;

	@Transactional
	public ArrayList<User> getAllUser() {
		return userDAO.getAllUser();
	}

	@Transactional
	public User getUser(String userName) {
		return userDAO.getUser(userName);
	}

	@Transactional
	public User addUser(User user) {
		return userDAO.addUser(user);
	}

	@Transactional
	public boolean updateUser(User user) {
		return userDAO.updateUser(user);
	}

	@Transactional
	public boolean deleteUser(String userName) {
		return userDAO.deleteUser(userName);
	}
}
