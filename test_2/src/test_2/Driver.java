package test_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Driver {
	
	static String url = "jdbc:mysql://localhost:3306/test";
	static String user = "root";
	static String password = "541548";
	
	public static void main(String[] args) {
		try {
			Connection myConn = DriverManager.getConnection(url, user, password);
			
			Statement myStatement = myConn.createStatement();
			
			String sql = "insert into employees "
					+ "(first_name, last_name, email) "
					+ "values ('Nina', 'Wang', 'wnn@gmail.com')";
			
			System.out.println(sql);
			
			// using executeUpdate function.
			myStatement.executeUpdate(sql);
			
			System.out.println("Insert complete.");
			
			sql = "select * from employees";
			
			ResultSet myRs = myStatement.executeQuery(sql);
			System.out.println(myRs);
			
			System.out.println("The results are: ");
			
			System.out.println("******************************");
			while (myRs.next()) {
				System.out.println(myRs.getString("first_name") + 
						" | " + myRs.getString("first_name") + 
						" | " + myRs.getString("email"));
			}
			System.out.println("******************************");
		} catch (SQLException e) {
			System.out.println("There are some exceptions.");
			e.printStackTrace();
		}
	}
}
