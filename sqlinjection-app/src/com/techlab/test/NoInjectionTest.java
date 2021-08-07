package com.techlab.test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class NoInjectionTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:4040/employee?" +
						 "user=root&password=root");
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter EmpNo:");
		String empno=sc.nextLine();
		String sql="SELECT ENAME,JOB FROM EMP WHERE EMPNO=?";
		PreparedStatement statement=(PreparedStatement) con.prepareStatement(sql);
		statement.setString(1, empno);
		ResultSet rs=statement.executeQuery();
		while(rs.next()) {
			String ename=rs.getString("ENAME");
			String job=rs.getString("JOB");
			System.out.println("Employee Name:"+ename);
			System.out.println("Employee Job:"+job);
		}

	}

}
