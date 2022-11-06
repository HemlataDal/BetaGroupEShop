package com.miniproject;


	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;
	import java.util.Scanner;

	// Class for inserting data from Admin into Database
	public class InsertProduct {

		Connection connection= null;

		PreparedStatement ps=null;

	// Method to insert data into Database
		public void adminInsertData(int productId,String p_Descreption,String name,int price,int quantity)  {

			try {

				ConnectionClass con=new ConnectionClass();

				connection=con.getConnection();

	// Insert Query 
				ps=connection.prepareStatement("insert into product(productId,productName,productdescription,price,quantity)values(?,?,?,?,?)");

				ps.setInt(1, productId);
				ps.setString(2,name );
				ps.setString(3,p_Descreption);
				ps.setInt(4, price);
				ps.setInt(5, quantity);

				int i=ps.executeUpdate();

				System.out.println("insert data >"+i);

				connection.close();
				ps.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {	
			}
		}
		
	}

