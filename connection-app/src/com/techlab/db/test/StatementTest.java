package com.techlab.db.test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class StatementTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?" +
						 "user=root&password=root");
		
		//Insert 
        String sql="INSERT INTO EMP(EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement statement=(PreparedStatement) con.prepareStatement(sql);
        statement.setInt(1, 100);
        statement.setString(2, "DEEPAK");
        statement.setString(3, "DEVELOPER");
        statement.setInt(4, 7839);
        statement.setString(5, "31-JULY-21");
        statement.setInt(6, 4000);
        statement.setInt(7, 500);
        statement.setInt(8, 10);
        
        int rowsInserted = statement.executeUpdate();

        if (rowsInserted > 0) {
            System.out.println("A new user was inserted successfully!");
            System.out.println("insert count:"+rowsInserted);
            printTable(con);
        }
        //Update
        String updateSql = "UPDATE EMP SET MGR=? WHERE ENAME=?";

        

        PreparedStatement updateStatement = (PreparedStatement) con.prepareStatement(updateSql);

        updateStatement.setInt(1,100);

        updateStatement.setString(2, "DEEPAK");

         

        int rowsUpdated = updateStatement.executeUpdate();

        if (rowsUpdated > 0) {

            System.out.println("An existing user was updated  successfully!");
            System.out.println("Update Count: "+rowsUpdated);
            printTable(con);
        }
        //Delete
        String deleteSql = "DELETE FROM EMP WHERE ENAME=?";

        

        PreparedStatement deleteStatement = (PreparedStatement) con.prepareStatement(deleteSql);

        deleteStatement.setString(1, "DEEPAK");

         

        int rowsDeleted = deleteStatement.executeUpdate();

        if (rowsDeleted > 0) {

            System.out.println("The user was deleted successfully!");
            System.out.println("Delete Count:"+rowsDeleted);
            printTable(con);
        }
        
	}

	private static void printTable(Connection con) throws SQLException {
		Statement stmt = (Statement) con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM emp");
        System.out.println("EMPNO    ENAME    JOB     MGR   HIREDATE   SAL   COMM   DEPTNO");
        
        while (rs.next()) {
           int id = rs.getInt("empno");
           String name = rs.getString("ename");
           String job = rs.getString("job");
           String mgr=rs.getString("mgr");
           String hireDate=rs.getString("hiredate");
           String sal=rs.getString("sal");
           String comm=rs.getString("comm");
           String deptNo=rs.getString("deptNo");
           System.out.println(id+"   "+name+"   "+job+"   "+mgr+"   "+hireDate+"   "+sal+"   "+comm+"   "+deptNo);
        }
		
	}

}
