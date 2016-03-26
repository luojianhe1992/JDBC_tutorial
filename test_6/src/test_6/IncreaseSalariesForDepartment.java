package test_6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;

public class IncreaseSalariesForDepartment {
	
	public static void main(String[] args) {
		
		Connection myConnection = null;
		java.sql.CallableStatement myStatment = null;
		
		String url = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String password = "541548";
		
		
		try {
			myConnection = DriverManager.getConnection(url, user, password);
			System.out.println("Connectioin complemented.");
			
			String department = "Engineering";
			int increment = 10000;
			
			
			
			String sql = "{call increase_salaries_for_department(?, ?)}";
			
			myStatment = myConnection.prepareCall(sql);
			
			myStatment.setString(1, department);
			myStatment.setInt(2, increment);
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void showSalaries(Connection myConnection, String department) throws SQLException{
		
		java.sql.PreparedStatement myStatement = null;
		ResultSet myResultSet = null;
		
		
		try {
			
			String sql = "select * from employees where department=?";
			
			myStatement = myConnection.prepareStatement(sql);
			
			myStatement.setString(1, department);
			
			myResultSet = myStatement.executeQuery();
			
			while (myResultSet.next()) {
				
				String the_first_name = myResultSet.getString("first_name");
				String the_last_name = myResultSet.getString("last_name");
				String the_email = myResultSet.getString("email");
				String the_department = myResultSet.getString("department");
				double the_salaries = myResultSet.getDouble("salaries");
				
				System.out.println(the_first_name + "\t"
								+ the_last_name + "\t"
								+ the_email + "\t"
								+ the_department + "\t"
								+ the_salaries);
				
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		finally {
			
		}
		
		
	}
	
	
	private static void close(Connection myConnection, Statement myStatment, ResultSet myResultSet) throws SQLException{
		
		if (myResultSet != null) {
			myResultSet.close();
		}
		
		if (myStatment != null) {
			myStatment.close();
		}
		
		if (myConnection != null) {
			myConnection.close();
		}
	}
	
	// overload the close function
	private static void close(Statement myStatement, ResultSet myResultSet) throws SQLException{
		
		if (myStatement != null) {
			myStatement.close();
		}
		
		if (myResultSet != null) {
			myResultSet.close();
		}
		
	}
	
	
	
	
}
