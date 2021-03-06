package test_13;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class WriteBlobDemo {
	public static void main(String[] args) throws SQLException, FileNotFoundException{
		
		Connection myConnection = null;
		PreparedStatement myStatement = null;
		FileInputStream input = null;
		
		String url = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String password = "541548";
		
		try {
			myConnection = DriverManager.getConnection(url, user, password);

			// the type of resume is BLOB
			String sql = "update employees set resume=? where email='john.doe@foo.com'";
			
			myStatement = myConnection.prepareStatement(sql);
			
			// new a File object
			File theFile = new File("sample_resume.pdf");
			System.out.println("The file is: " + theFile);
			
			// new a FileInputStream object
			input = new FileInputStream(theFile);
			System.out.println("The input is: " + input);
			
			myStatement.setBinaryStream(1, input);
			
			myStatement.executeUpdate();
			
			System.out.println("Update successfully");
			
		} catch (SQLException e) {
			System.out.println("This is SQLException");
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			System.out.println("This is FileNotFoundException");
			e.printStackTrace();
		}
		finally {
			close(myConnection, myStatement);
		}
		
	}
	
	private static void close(Connection myConnection, Statement myStatement) throws SQLException {
		if (myStatement != null) {
			myStatement.close();
		}
		
		if (myConnection != null) {
			myConnection.close();
		}
	}
}
