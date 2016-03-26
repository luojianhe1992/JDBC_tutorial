package test_7;

import java.awt.Window.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class GreetTheDepartment {
	public static void main(String[] args) throws SQLException{
		
		Connection myConnection = null;
		CallableStatement myStatement = null;
		
		String url = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String password = "541548";
		
		try {
			
			myConnection = DriverManager.getConnection(url, user, password);
			
			String department = "Engineering";
			
			System.out.println("Before calling the greet_the_department procedure");
			showEmployers(myConnection, department);
			
			String sql = "{call greet_the_department(?)}";
			
			myStatement = myConnection.prepareCall(sql);
			
			myStatement.registerOutParameter(1, Types.VARCHAR);
			myStatement.setString(1, department);
			
			System.out.println("Is going to call stored procedure, greet_the_department.");
			myStatement.execute();
			System.out.println("Successfully call stored procedure, greet_the_department.");
			
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			String theResult = myStatement.getString(1);
			System.out.println("The stored procedure result is: " + theResult);
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			
			System.out.println("After calling the greet_the_department procedure, show all employers");
			showAllEmployers(myConnection);
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		finally {
			close(myConnection, myStatement, null);
		}
	}
	
	// show all employers
	private static void showAllEmployers(Connection myConnection) throws SQLException{
		java.sql.PreparedStatement myStatement = null;
		ResultSet myResultSet = null;
		try {
			String sql = "select * from employees";
			myStatement = myConnection.prepareStatement(sql);
			myResultSet = myStatement.executeQuery();
			System.out.println("*********************************************");
			while (myResultSet.next()) {
				String the_first_name = myResultSet.getString("first_name");
				String the_last_name = myResultSet.getString("last_name");
				String the_email = myResultSet.getString("email");
				String the_department = myResultSet.getString("department");
				double the_salaries = myResultSet.getDouble("salary");
				System.out.println(the_first_name + "\t"
								+ the_last_name + "\t"
								+ the_email + "\t"
								+ the_department + "\t"
								+ the_salaries);
			}
			System.out.println("*********************************************");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(myStatement, myResultSet);
		}
	}
	
		
	// Show the employers according to the department
	private static void showEmployers(Connection myConnection, String department) throws SQLException{
	
		java.sql.PreparedStatement myStatement = null;
		ResultSet myResultSet = null;
		
		try {
			
			String sql = "select * from employees where department=?";
			
			myStatement = myConnection.prepareStatement(sql);
			
			myStatement.setString(1, department);
			
			myResultSet = myStatement.executeQuery();
			
			System.out.println("*********************************************");
			while (myResultSet.next()) {
				String the_first_name = myResultSet.getString("first_name");
				String the_last_name = myResultSet.getString("last_name");
				String the_email = myResultSet.getString("email");
				String the_department = myResultSet.getString("department");
				double the_salaries = myResultSet.getDouble("salary");
				System.out.println(the_first_name + "\t"
								+ the_last_name + "\t"
								+ the_email + "\t"
								+ the_department + "\t"
								+ the_salaries);
			}
			System.out.println("*********************************************");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(myStatement, myResultSet);
		}
	}
	
	
	// define close function
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
