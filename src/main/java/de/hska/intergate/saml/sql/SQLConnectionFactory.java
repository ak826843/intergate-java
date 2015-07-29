package de.hska.intergate.saml.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.prefs.Preferences;

public class SQLConnectionFactory {
	static Preferences preferences = Preferences
			.userNodeForPackage(SQLConnectionFactory.class);
	
	public static final String mysqlConnector = "jdbc:mysql://";
	
	public static Connection create() {
		String username = preferences.get("db_username", null);
		String password =  preferences.get("db_password", null);
		String database = preferences.get("db_database", null);
		String address = preferences.get("db_address", null);
		
		if(username == null) {
			Scanner input = new Scanner(System.in);
			
			System.out.print("Enter the name of the database user: ");
			username = input.next();
			preferences.put("db_username", username);
			
			input.close();
		}
		if (password == null) {
			Scanner input = new Scanner(System.in);
			
			System.out.print("Enter the password of the database user: ");
			password = input.next();
			preferences.put("db_password", password);
			
			input.close();
		}
		if (database == null) {
			Scanner input = new Scanner(System.in);
			
			System.out.print("Enter the name of the database: ");
			database = input.next();
			preferences.put("db_database", database);
			
			input.close();
		}
		if (address == null) {
			Scanner input = new Scanner(System.in);
			
			System.out.print("Enter the address of the database ([sub.]domain.com[:port]): ");
			address = input.next();
			preferences.put("db_address", address);
			
			input.close();
		}
		
		Connection connection = null;
		String databaseURI = mysqlConnector + address + "/" + database;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {

			connection = DriverManager.getConnection(databaseURI,username,password);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}

		return connection;
	}
}
