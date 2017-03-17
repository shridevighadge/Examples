package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.UserDao;
import com.websystique.springmvc.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	public User findById(long id) {
		return userDao.findById(id);
	}

	public User findByName(String name) {
		return userDao.findByName(name);
	}

	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public void deleteUserById(long id) {
		userDao.deleteUserById(id);
	}

	public boolean isUserExist(User user) {
		if (user.getUsername() != null) {
			return userDao.isUserExist(user);
		}
		return false;
	}

	public void deleteAllUsers() {
		userDao.deleteAllUsers();
	}

}
