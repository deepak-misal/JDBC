package com.techlab.db.test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class TransactionTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/transaction?" +
						 "user=root&password=root");
		
		//stmt.executeUpdate("UPDATE CUSTOMER SET CBALANCE=(CBALANCE+2000) WHERE CID=101");
		//stmt.executeUpdate("UPDATE MERCHANT SET BALANCE=(BALANCE+2000) WHERE MRID=10");
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Purchase amount:");
		int amount=sc.nextInt();
		System.out.println("Enter Merchant Id:");
		int mid=sc.nextInt();
		System.out.println("Enter Customer Id:");
		int cid=sc.nextInt();
		System.out.println("Before Transaction:\n");
		printTable(con);
		
		con.setAutoCommit(false);
		try {
			
			Statement stmt = (Statement) con.createStatement();
			int stmt1=stmt.executeUpdate("UPDATE CUSTOMER SET CBALANCE=(CBALANCE-"+amount+") WHERE CID="+cid);
			int stmt2=stmt.executeUpdate("UPDATE MERCHANT SET BALANCE=(BALANCE+"+amount+") WHERE MRID="+mid);
			if((stmt1==0)||(stmt2==0)) {
				con.rollback();
				throw new SQLException("Transaction Failed!");
			}
			con.commit();
			stmt.close();
			System.out.println("Trsaction completed!\n");
			System.out.println("After Transaction:\n");
			printTable(con);
			
		}catch(SQLException e){
			con.rollback();
			System.out.println("Transaction failed!");
		}
	}

	private static void printTable(Connection con) throws SQLException {
		Statement stmt1 = (Statement) con.createStatement();
		ResultSet rs=stmt1.executeQuery("SELECT * FROM MERCHANT");
		System.out.println("MRID  MNAME  BALANCE");
		while(rs.next()) {
			int id=rs.getInt("MRID");
			String name=rs.getString("MNAME");
			int balance=rs.getInt("BALANCE");
			System.out.println(id+"   "+name+"   "+balance+"\n");
		}
		ResultSet rs1=stmt1.executeQuery("SELECT * FROM CUSTOMER");
		System.out.println("CID  CNAME  CBALANCE");
		while(rs1.next()) {
			int id=rs1.getInt("CID");
			String name=rs1.getString("CNAME");
			int balance=rs1.getInt("CBALANCE");
			System.out.println(id+"   "+name+"   "+balance);
		}
		
	}

}
