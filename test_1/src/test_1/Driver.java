package test_1;

import java.sql.*;

public class Driver {

	public static void main(String[] args) {
		
		try{
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "541548");
			System.out.println("Successfully connect to the mysql database.");
			
			Statement myStmt = myConn.createStatement();
			
			ResultSet myRs = myStmt.executeQuery("select * from employees");
			System.out.println(myRs);
			
			System.out.println("******************************");
			while (myRs.next()) {
				System.out.println(myRs.getString("first_name") + " " + myRs.getString("first_name"));
			}
			System.out.println("******************************");
			
		}
		catch (Exception exc){
			exc.printStackTrace();
		}
		
	}

}
