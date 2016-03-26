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
	
	public static void main(String[] args) throws SQLException {
		
		Connection myConnection = null;
		java.sql.PreparedStatement myPrepareStatement = null;
		ResultSet myResultSet = null;
		
		try {
			myConnection = DriverManager.getConnection(url, user, password);
			
			String preparedSQL = "select * from employees where salary >= ? and department=?";
			
			myPrepareStatement = myConnection.prepareStatement(preparedSQL);
			
			// set parameter for prestatement
			myPrepareStatement.setInt(1, 7000);
			myPrepareStatement.setString(2, "OA");
			
			myResultSet = myPrepareStatement.executeQuery();
			
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
		finally {
			close(myConnection, myPrepareStatement, myResultSet);
		}
	}
	
	// close the connection statement and resultset.
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
	
	// overload close function.
	private static void close(Statement myStatement, ResultSet myResultSet) throws SQLException{
		close(null, myStatement, myResultSet);
	}
	
}
