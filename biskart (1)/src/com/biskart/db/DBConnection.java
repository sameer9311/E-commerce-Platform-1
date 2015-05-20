package com.biskart.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	/**
	 * @param args
	 */
	public static Connection getConnection() {
		Connection conn=null;	
		try {		
			Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BISdb","root","root");
		
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;		
	}

}