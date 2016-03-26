package test_5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class Driver {

	static String url = "jdbc:mysql://localhost:3306/test";
	static String user = "root";
	static String password = "541548";
	
	public static void main(String[] args) {
		try {
			Connection myConnection = DriverManager.getConnection(url, user, password);
			
			String preparedSQL = "select * from employees where salary >= ? and department=?";
			
			java.sql.PreparedStatement myPreparedStatement = myConnection.prepareStatement(preparedSQL);
			
			myPreparedStatement.setInt(1, 7000);
			myPreparedStatement.setString(2, "OA");
			
			ResultSet myResultSet = myPreparedStatement.executeQuery();
			
			System.out.println("**********************************");
			while (myResultSet.next()) {
				System.out.println(myResultSet.getString("first_name") + " | "
								+ myResultSet.getString("last_name") + " | "
								+ myResultSet.getString("email") + " | "
								+ myResultSet.getInt("salary") + " | "
								+ myResultSet.getString("department"));
			}			
			System.out.println("**********************************");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
