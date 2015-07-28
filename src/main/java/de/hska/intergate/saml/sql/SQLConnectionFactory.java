package de.hska.intergate.saml.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnectionFactory {
	public static final String mysqlAddress = "";
	public static final String mysqlDatabase = "";
	public static final String mysqlUsername = "";
	public static final String mysqlPassword = "";

	public static Connection create() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			
			connection = DriverManager.getConnection(mysqlAddress
					+ mysqlDatabase, mysqlUsername, mysqlPassword);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}

		return connection;
	}
}
