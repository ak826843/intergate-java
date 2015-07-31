package de.hska.intergate.saml.web;

import de.hska.intergate.saml.manage.User;
import de.hska.intergate.saml.manage.dao.RoleDao;
import de.hska.intergate.saml.manage.dao.RoleDaoImpl;
import de.hska.intergate.saml.manage.dao.UserDao;
import de.hska.intergate.saml.manage.dao.UserDaoImpl;

public class Intergate {
	public User getUserByMail(String mail) {
		User user = null;
		
		UserDao userDao = new UserDaoImpl();
		user = userDao.getUserByMail(mail);
		RoleDao roleDao = new RoleDaoImpl();
		user.setRoles(roleDao.getRolesByUser(user));
		
		return user;
	}
}
