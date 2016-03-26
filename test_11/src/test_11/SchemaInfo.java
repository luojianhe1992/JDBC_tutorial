package test_11;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchemaInfo {
	public static void main(String[] args) throws SQLException{
		
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = null;
		String columnNamePattern = null;
		String [] types = null;
		
		Connection myConnection = null;
		ResultSet myResultSet = null;
		
		String url = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String password = "541548";
		
		try {
			myConnection = DriverManager.getConnection(url, user, password);
			
			DatabaseMetaData myDatabaseMetaData = myConnection.getMetaData();
			
			System.out.println("List of tables:");
			System.out.println("----------------------------");

			myResultSet = myDatabaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types);
			
			while (myResultSet.next()) {
				String tableName = myResultSet.getString("TABLE_NAME");
				System.out.println("TABLE_NAME is: " + tableName);
			}
			
			System.out.println("----------------------------");

			System.out.println();
			
			System.out.println("List of columns:");
			System.out.println("----------------------------");

			tableNamePattern = "employees";
			myResultSet = myDatabaseMetaData.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);
			
			while (myResultSet.next()) {
				String columnName = myResultSet.getString("COLUMN_NAME");
				System.out.println(columnName);
			}
			
			System.out.println("----------------------------");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(myConnection, myResultSet);
		}
		
		
	}
	
	private static void close(Connection myConnection, ResultSet myResultSet) throws SQLException{
		
		if (myResultSet != null) {
			myResultSet.close();
		}
		
		if (myConnection != null) {
			myConnection.close();
		}
	}
	
}
