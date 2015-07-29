package de.hska.intergate.saml.manage.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.hska.intergate.saml.manage.Role;
import de.hska.intergate.saml.manage.User;
import de.hska.intergate.saml.sql.SQLConnectionFactory;

public class RoleDaoImpl implements RoleDao {
	Connection dbConnection = null;
	Statement statement = null;

	public RoleDaoImpl() {
		try {
			dbConnection = SQLConnectionFactory.create();
			statement = dbConnection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Role createRole(Role role) {
		String sql = "INSERT INTO rollen(referenz, alias) VALUES ('"
				+ role.getReference() + "','" + role.getAlias() + "')";
		try {
			statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			Role r = getRoleByReference(role.getReference());
			role.setRid(r.getRid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}

	@Override
	public List<Role> getAllRoles() {
		List<Role> roles = new ArrayList<Role>();

		String sql = "SELECT * FROM rollen";

		try {
			ResultSet rs = statement.executeQuery(sql);
			Role role = null;
			while (rs.next()) {
				role = new Role(rs.getInt("rid"), rs.getString("referenz"),
						rs.getString("alias"));
				roles.add(role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roles;
	}

	@Override
	public Role getRoleByReference(String reference) {
		Role role = null;

		String sql = "SELECT * FROM rollen WHERE referenz ='" + reference + "'";

		try {
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				role = new Role(rs.getInt("rid"), rs.getString("referenz"),
						rs.getString("alias"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return role;
	}

	@Override
	public int updateRole(Role role) {
		int code = -1;
		String sql = "UPDATE rollen SET referenz='" + role.getReference()
				+ "', alias='" + role.getAlias() + "' WHERE rid="
				+ role.getRid();

		try {
			code = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return code;
	}

	@Override
	public int deleteRole(Role role) {
		int code = -1;
		String sql = "DELETE FROM rollen WHERE rid=" + role.getRid();
		try {
			code = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return code;
	}

	@Override
	public List<Role> getAllRolesByUser(User user) {
		List<Role> roles = new ArrayList<Role>();
		String sql = "SELECT * FROM rollen r JOIN benutzerrollen b ON b.rid = r.rid WHERE b.bid ="
				+ user.getUid();
		try {
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				roles.add(new Role(rs.getInt("rid"),rs.getString("referenz"),rs.getString("alias")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}

}
