package test_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Driver {
	
	static String url = "jdbc:mysql://localhost:3306/test";
	static String user = "root";
	static String password = "541548";
	
	public static void main(String[] args) {
		try {
			Connection myConn = DriverManager.getConnection(url, user, password);
			System.out.println("Successfully connected to the mysql database.");
			
			Statement myStatement = myConn.createStatement();
			
			String sql = "update employees"
					+ " set email='jian@andrew.cmu.edu'"
					+ " where first_name='Jianhe' and last_name='Luo'";
			
			myStatement.executeUpdate(sql);
			System.out.println("Update completed.");

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
