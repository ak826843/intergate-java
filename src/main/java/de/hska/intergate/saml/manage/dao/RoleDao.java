package de.hska.intergate.saml.manage.dao;

import java.util.List;

import de.hska.intergate.saml.manage.Role;
import de.hska.intergate.saml.manage.User;

public interface RoleDao {
	public Role createRole(Role role);

	public List<Role> getAllRoles();

	public Role getRoleByReference(String reference);

	public int updateRole(Role role);

	public int deleteRole(Role role);
	
	public List<Role> getRolesByUser(User user);
}
