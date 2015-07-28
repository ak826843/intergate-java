package de.hska.intergate.saml.manage.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.hska.intergate.saml.manage.User;
import de.hska.intergate.saml.sql.SQLConnectionFactory;

public class UserDaoImpl implements UserDao {
	Connection dbConnection = null;
	Statement statement = null;

	public UserDaoImpl() {
		try {
			dbConnection = SQLConnectionFactory.create();
			statement = dbConnection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();

		String sql = "SELECT * FROM benutzer";

		try {
			ResultSet rs = statement.executeQuery(sql);
			User user = null;
			while (rs.next()) {
				user = new User(rs.getInt("bid"), rs.getString("email"),
						rs.getString("alias"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User getUser(String email) {
		User user = null;

		String sql = "SELECT * FROM benutzer WHERE email ='" + email + "'";

		try {
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				user = new User(rs.getInt("bid"), rs.getString("email"),
						rs.getString("alias"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public int updateUser(User user) {
		int code = -1;
		String sql = "UPDATE benutzer SET email='" + user.getEmail()
				+ "', alias='" + user.getAlias() + "' WHERE bid="
				+ user.getUid();

		try {
			code = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return code;
	}

	@Override
	public int deleteUser(User user) {
		int code = -1;
		String sql = "DELETE FROM benutzer WHERE bid=" + user.getUid();
		try {
			code = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return code;
	}

	@Override
	public User createUser(User user) {
		String sql = "INSERT INTO benutzer (email, alias) VALUES ('"+user.getEmail()+"','"+user.getAlias()+"')";
		try {
			statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			User u = getUser(user.getEmail());
			user.setUid(u.getUid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
