package test_12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetDemo {
	public static void main(String[] args) throws SQLException{
		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultSet = null;
		
		String url = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String password = "541548";
		
		try {
			
			myConnection = DriverManager.getConnection(url, user, password);
			
			myStatement = myConnection.createStatement();
			
			String sql = "select id, first_name, last_name, email, salary from employees";
			
			myResultSet = myStatement.executeQuery(sql);
			
			ResultSetMetaData myResultSetMetaData = myResultSet.getMetaData();
			
			System.out.println("ResultSet MetaData information:");
			System.out.println("------------------------------------------------");
			int columnCount = myResultSetMetaData.getColumnCount();
			System.out.println("The column count is: " + columnCount);
			System.out.println("*********************");
			
			for (int i = 1; i <= columnCount; i++) {
				System.out.println("Column name is: " + myResultSetMetaData.getColumnName(i));
				System.out.println("Column type name is: " + myResultSetMetaData.getColumnTypeName(i));
				System.out.println("Is nullable: " + myResultSetMetaData.isNullable(i));
				System.out.println("Is auto increment: " + myResultSetMetaData.isAutoIncrement(i));
				System.out.println();
			}
			
			System.out.println("------------------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(myConnection, myStatement, myResultSet);
		}
	}
	
	private static void close(Connection myConnection, Statement myStatement, ResultSet myResultSet) throws SQLException{
		if (myResultSet != null) {
			myResultSet.close();
		}
		
		if (myStatement != null) {
			myStatement.close();
		}
		
		if (myConnection != null) {
			myConnection.close();
		}
	}
	
	
}
