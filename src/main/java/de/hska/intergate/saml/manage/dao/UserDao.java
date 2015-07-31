package de.hska.intergate.saml.manage.dao;

import java.util.List;

import de.hska.intergate.saml.manage.Role;
import de.hska.intergate.saml.manage.User;

public interface UserDao {
	public User createUser(User user);

	public List<User> getAllUsers();

	public User getUserByMail(String email);
	
	public User getUserById(int id);

	public int updateUser(User user);

	public int deleteUser(User user);
	
	public void addRoleToUser(User user, Role role);

	public List<String> getAllUserRoles(User user);
}
