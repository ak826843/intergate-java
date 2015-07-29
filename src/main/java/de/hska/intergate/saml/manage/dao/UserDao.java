package de.hska.intergate.saml.manage.dao;

import java.util.List;

import de.hska.intergate.saml.manage.User;

public interface UserDao {
	public User createUser(User user);
	public List<User> getAllUsers();
	public User getUserByMail(String email);
	public int updateUser(User user);
	public int deleteUser(User user);
}
