package test_14;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WriteClobDemo {
	public static void main(String[] args) throws SQLException, FileNotFoundException{
		Connection myConnection = null;
		PreparedStatement myStatement = null;
		ResultSet myResultSet = null;
		
		String url = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String password = "541548";
		
		FileReader input = null;
		
		try {
			myConnection = DriverManager.getConnection(url, user, password);
			String sql = "update employees set resume=? where email='john.doe@foo.com'";
			myStatement = myConnection.prepareStatement(sql);
			
			File theFile = new File("sample_resume.txt");
			System.out.println("The input File object is: " + theFile);
			
			input = new FileReader(theFile);
			System.out.println("The input FileReader object is: " + input);
			
			myStatement.setCharacterStream(1, input);
			
			System.out.println("Is going to executeUpdate to the database");
			myStatement.executeUpdate();
			System.out.println("Successfully Stored the input file: " + theFile.getAbsolutePath() + "into database");
			
		} catch (SQLException e) {
			System.out.println("This is SQLException");
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			System.out.println("This is FileNotFoundException");
			e.printStackTrace();
		}
		finally {
			close(myConnection, myStatement, myResultSet);
		}
		
		
	}
	
	private static void close(Connection myConnection, Statement myStatement, ResultSet myResultSet) throws SQLException {
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
