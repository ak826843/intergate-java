package de.hska.intergate.saml.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class SQLConnectionFactory {
	static Preferences preferences = Preferences
			.userNodeForPackage(SQLConnectionFactory.class);
	
	public static final String mysqlAddress = "jdbc:mysql://thesis.deltatm.org:3306/";
	public static final String mysqlDatabase = "intergate";
	
	public static Connection create() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {

			connection = DriverManager.getConnection(mysqlAddress
					+ mysqlDatabase, preferences.get("db_username", null), preferences.get("db_password", null));

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}

		return connection;
	}
}
