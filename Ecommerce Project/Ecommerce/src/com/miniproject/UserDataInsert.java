package com.miniproject;


	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Scanner;

	// Class to insert data into cart by Buyer
	public class UserDataInsert {
		
		PreparedStatement ps=null;
		Connection connection=null;
		
	// Method for add to cart	
		public void userdata(int productid,int userid,int quantity) throws SQLException {
			
		try {
			         ConnectionClass con=new ConnectionClass();
	                 connection=con.getConnection();
	                 
	// Display Product Details
	 ps=connection.prepareStatement("select * from product  where productId=?");
	         		
	         	ps.setInt(1,productid);
	    		ResultSet rs=ps.executeQuery();
	    		
	// Insert Data into cart by Product id and user id         		
	         		while(rs.next()) {
	         			 ps=connection.prepareStatement("select cartId from usercarts  where productId=? and UserId=?");
	         			ps.setInt(1, rs.getInt(1));
	         			ps.setInt(2, userid);
	         			ResultSet rs1=ps.executeQuery();
	         			
	// Checking if product is already present or not if present update quantity in cart        			
	         			boolean productpresent=true;
	         			while(rs1.next()) {
	         				
	         				 ps=connection.prepareStatement("Update usercarts set quantity=quantity+?  where productId=? and UserId=?");
	         				ps.setInt(2, rs.getInt(1));
	             			ps.setInt(3, userid);
	             			ps.setInt(1, quantity);
	             			int i=ps.executeUpdate();
	             			
	             			if(i>0) {
	             				System.out.println("Your cart is updated");
	             				
	// Updating Product Table Quantities             				
	             				productpresent=false;
	             				ps=connection.prepareStatement("update product set quantity=quantity-? where productId=?");
	                 			ps.setInt(2, rs.getInt(1));
	                 			ps.setInt(1, quantity);
	                 			int j=ps.executeUpdate();
	             			}
	         				}
	         			if(productpresent) {
	         				ps=connection.prepareStatement("insert into UserCarts(productId,userId,quantity) values(?,?,?)");
	             			ps.setInt(1, rs.getInt(1));
	             			ps.setInt(2, userid);
	             			ps.setInt(3, quantity);
	             			int i=ps.executeUpdate();
	             			if(i>0) {
	             				System.out.println("***Added data into cart***");
	             				
	                 			ps=connection.prepareStatement("update product set quantity=quantity-? where productId=?");
	                 			ps.setInt(2, rs.getInt(1));
	                 			ps.setInt(1, quantity);
	                 			int j=ps.executeUpdate();
	             			}else {
	             				System.out.println("***Not added data into cart***");
	             			}
	         			}
	         			
	        		}
	         		rs.close();
	         	
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
			connection.close();
		}
		}

	}

