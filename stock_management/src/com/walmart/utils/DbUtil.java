package com.walmart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	static Connection connect=null;
	
	public static Connection getConnection(String database) throws SQLException {
	
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database, "stalker", "stocks");
		return connect;
	}
	public static Connection getConnection(String database, String user, String pass) throws SQLException {

		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database, user, pass);
		return connect;
	}
	private DbUtil() {}
}

