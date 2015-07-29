package de.hska.intergate.saml.manage.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hska.intergate.saml.manage.User;
import de.hska.intergate.saml.manage.dao.UserDao;
import de.hska.intergate.saml.manage.dao.UserDaoImpl;

@RestController
public class UserController {
	
	@RequestMapping("/user")
	public List<User> getUser(){
		UserDao userDao = new UserDaoImpl();
		return userDao.getAllUsers();
	}
}
