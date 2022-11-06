package com.miniproject;


	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	
	

	// Class to see the Product data in ascending order
	public class FetchData {
		public static void main(String[] args)
		{
			FetchData f=new FetchData();
			f.fetchData();
		}

		Connection connection= null;

		PreparedStatement ps=null ;
		
	// Method to see the Product data in ascending order
		public void fetchData() {
			try {

				ConnectionClass con=new ConnectionClass ();

				connection=con.getConnection();

	         // Ascending Order Query
				PreparedStatement ps=connection.prepareStatement("select * from product order by productName ");
				ResultSet rs=ps.executeQuery();
				System.out.println("productid\tProductName\t\tProduct Descreption\t\t\t\tprice\t\tquantity");
				while(rs.next()) {
					System.out.println("----------------------------------------------------------------------------------------------------------------");
				//System.out.println(rs.getInt(1)+"|\t\t"+rs.getString(2)+"|\t"+rs.getString(3)+"|\t\t"+rs.getInt(4)+"|\t\t\t"+rs.getInt(5));
					System.out.print(rs.getInt(1));
					System.out.print("\t"+rs.getString(2));
					System.out.print("\t\t"+rs.getString(3));
					System.out.print("\t\t\t"+rs.getInt(4));
					System.out.println("\t\t\t"+rs.getString(5));
                    System.out.println("----------------------------------------------------------------------------------------------------------------");
				}
				connection.close();
				ps.close();
				rs.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}	
		}
}
