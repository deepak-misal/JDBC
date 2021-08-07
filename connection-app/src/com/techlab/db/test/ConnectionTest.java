package com.techlab.db.test;

import java.sql.DriverManager;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnectionTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?" +
						 "user=root&password=root");
		System.out.println("Class: "+conn.getClass());
		System.out.println("Database Name: "+conn.getCatalog());
		System.out.println("Server name:"+conn.getHost());
		conn.close();
	}

}
