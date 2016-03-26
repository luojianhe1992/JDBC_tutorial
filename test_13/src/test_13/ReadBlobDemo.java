package test_13;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadBlobDemo {
	public static void main(String[] args) throws SQLException, IOException{
		
		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultSet = null;
		
		String url = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String password = "541548";
		
		FileOutputStream output = null;
		InputStream input = null;
		
		try {
			myConnection = DriverManager.getConnection(url,user, password);
			myStatement = myConnection.createStatement();
			String sql = "select resume from employees where email='john.doe@foo.com'";
			myResultSet = myStatement.executeQuery(sql);
			
			// create new File object and new FileOutputStream
			File theFile = new File("resume_from_db.pdf");
			output = new FileOutputStream(theFile);
			
			if (myResultSet.next()) {
				// get binary stream from ResultSet about resume
				input = myResultSet.getBinaryStream("resume");
				System.out.println("The InputStream from database about resume is: " + input);
				
				System.out.println("Reading resume from database...");
				System.out.println();
				
				// using byte to write the FileOutputStream object
				byte [] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}
				
				System.out.println("Already saved the file to: " + theFile.getAbsolutePath());
				System.out.println("Mission completed");
			}
		} catch (SQLException e) {
			System.out.println("This is SQLException");
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			System.out.println("This is FileNotFoundException");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("This is IOException");
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