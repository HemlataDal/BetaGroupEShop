package com.miniproject;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Scanner;

	// Class to get the Cart Details
	public class ShowCart {

		PreparedStatement ps=null;
		Connection connection=null;

	// Method to get the Cart Details
		public void showCart(int userid) {
			try {
				ConnectionClass con=new ConnectionClass();

			connection=con.getConnection();

	// Cart Details Query
				ps=connection.prepareStatement("select p.productId,p.productdescription,p.productName,u.quantity,p.price,u.quantity*p.price as total"
						+ " from UserCarts u ,product p   where userId=? and u.productId=p.productId");

				ps.setInt(1,userid);
				ResultSet rs=ps.executeQuery();
				System.out.println("productId\t\tproductdescription\t\tproductname\t\tquantity\tprice\t\ttotal");
				long carttotal=0;
				
	// Printing cart details			
				while(rs.next())
				{
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t"+rs.getInt(4)+"\t"+rs.getInt(5)+
							"\t\t"+rs.getInt(6));

					carttotal=carttotal+rs.getInt(6);

				}

				System.out.println("Your total bill is="+carttotal);
				System.out.println("****************THANKS VISIT AGAIN**********");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

