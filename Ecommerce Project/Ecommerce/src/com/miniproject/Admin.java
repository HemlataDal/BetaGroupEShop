package com.miniproject;


	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Scanner;

	// Class for Admin to get Buyer Details
	public class Admin {

		PreparedStatement ps=null;
		Connection connection=null;
		
	// Method for Admin to get Buyer Details	
		public void showAdmindata()	{
			try {
				ConnectionClass con=new ConnectionClass ();

				connection=con.getConnection();
				ps=connection.prepareStatement("select * from user");

	// Query to get Buyer details
				System.out.println("userId\tfirstname\tlastname");
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getString(3));
				}
			} catch ( SQLException e) {

				e.printStackTrace();
			}
			
		}

	}

