package com.miniproject;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;
	import java.util.Scanner;

	// Class to get the information of Buyer
	public class RegisterInfo {
			
		Connection connection= null;
	    PreparedStatement ps=null ;
	    
	// Method to get information of Buyer
		public void registerInsert(String firstname,String lastname,String password) throws SQLException {

			try {

				ConnectionClass con=new ConnectionClass ();

				connection=con.getConnection();
				
	// User Information insert Query			
				ps=connection.prepareStatement("insert into user(firstname,lastname,password)values(?,?,?)");

				ps.setString(1, firstname);
				ps.setString(2, lastname);
				ps.setString(3, password);
				
				int i=ps.executeUpdate();

				System.out.println("insert data >"+i);



			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				connection.close();

				ps.close();}
		}
		}
	

