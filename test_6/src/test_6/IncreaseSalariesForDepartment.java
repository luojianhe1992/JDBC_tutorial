package test_6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;

public class IncreaseSalariesForDepartment {
	
	public static void main(String[] args) throws SQLException {
		
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
			
			System.out.println("Before calling the stored procedure.");
			showEmployers(myConnection, department);
			
			// call precedure in the sql
			String sql = "{call increase_salaries_for_department(?, ?)}";
			myStatment = myConnection.prepareCall(sql);
			
			myStatment.setString(1, department);
			myStatment.setInt(2, increment);
				
			System.out.println("Is going to call stored precedure, increase_salaries_for_department, "
					+ "and set department to be Engineering, increment to be 10000.");
			
			myStatment.execute();
			System.out.println("Finish calling stored procedure.");
			
			System.out.println("After calling the procedures.");
			showEmployers(myConnection, department);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(myConnection, myStatment, null);
		}
	}
	
	
	public static void showEmployers(Connection myConnection, String department) throws SQLException{
		
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
