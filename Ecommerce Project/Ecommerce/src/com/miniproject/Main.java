package com.miniproject;
import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Scanner;

    
    public class Main {
    	// Main Class
    
    	public static void main(String[] args) throws SQLException, ClassNotFoundException {
    	//  Main Method
    
    		System.out.println("*************************************************");
    	    System.out.println("           $ Welcome to BeautyWorld $            ");
    	    System.out.println("*************************************************");
    	    
    	    System.out.println("--------------------------------------------------");
    	    System.out.println(" 1)ADMIN                             2)USER       ");
    	    System.out.println("---------------------------------------------------");
    	    
    	    System.out.println("***************************************************");
    	    System.out.println("                     *   REGISTER  *                  ");
    	    System.out.println("****************************************************");
    	    System.out.println("                     *    LOGIN    *                  ");
    	    System.out.println("****************************************************");
    	 // Declaring Variables and Calling Objects
    	    
    		String firstname, lastname, password, ch = "";
    		String p_Descreption, name = "";
    		int price,productId, quantity = 0;
    		ResultSet rs =null;
    		int userid=0;
    		int j = 111; 
    		int n = 0;
    		
    		UserDataInsert usd=new UserDataInsert();
    		ShowCart showcart=new ShowCart();
    		FetchData fetchData=new FetchData();
    		Admin d = new Admin();
    		InsertProduct ip=new InsertProduct();
    		
    		
    		PreparedStatement ps=null;
    		Connection connection=null;
    		ConnectionClass con=new ConnectionClass ();
    		connection=con.getConnection();
    		Scanner scanner = new Scanner(System.in);
    		do {
    			System.out.println("Welcome !");
    			System.out.println("\nEnter 1 for Admin \nEnter 2 for Buyer");
    			int i = scanner.nextInt();
    // Admin Entry
    			if (i == 1) {
    				System.out.println("\n\nEnter passkey");
    				int k = scanner.nextInt();
    // Checking Admin Passkey				
    				if (j == k) {
    					System.out.println("\nEnter 1 to insert product details \nEnter 2 to check product quantity \nEnter 3 to check user details");
    					n=scanner.nextInt();
    					switch (n) {
    // Inserting Product Details					
    					case 1:
    						System.out.println("\nEnter number of products youwish to enter");
    						int num = scanner.nextInt();
    						for( i=0;i<num;i++)
    						{
    							System.out.println("Enter product id>>");
    							productId=scanner.nextInt();
    							
    							
    							System.out.println("Enter Product Descreption");
    							p_Descreption=scanner.next();

    							System.out.println("Enter Product Name");
    							name=scanner.next();

    							System.out.println("Enter Price");
    							price=scanner.nextInt();

    							System.out.println("Enter Quantity");
    							quantity=scanner.nextInt();           

    							ip.adminInsertData(productId,p_Descreption, name, price, quantity);
    						}
    						break;
    // Checking Product Quantities						
    					case 2:
    						ps=connection.prepareStatement("select * from product order by productName asc");
    						rs=ps.executeQuery();
    						System.out.println("productid\tp_Descreption\t\tname\t\tprice\t\tquantity");
    						while(rs.next()) {
    							System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getInt(4)+"\t\t"+rs.getInt(5));

    						}
    						break;
    // To Check Buyer Data
    					case 3:
    						d.showAdmindata();

    						System.out.println("Enter user id");
    						userid=scanner.nextInt();

    						showcart.showCart(userid);
    						break;
    					}
    				}
    				else {
    					System.out.println("Invalid Admin");
    				}
    			}
    // Buyer Entry 			
    			else if(i == 2) {
    				System.out.println("\nEnter 1 for Register \nEnter 2 for Login");
    				int choice = scanner.nextInt();
    				switch(choice) {
    // For New Buyer			
    				case 1: 
    					RegisterInfo ri=new RegisterInfo();
    					System.out.println("Enter Firstname");
    					firstname=scanner.next();

    					System.out.println("Enter Lastname");
    					lastname=scanner.next();


    					System.out.println("Enter Password");
    					password=scanner.next();

    					ri.registerInsert(firstname, lastname, password); 
    					ps=connection.prepareStatement("select userId from user where firstname=? and password=?");
    					ps.setString(1, firstname);
    					
    					ps.setString(2 , password);
    					rs=ps.executeQuery();

    					while(rs.next()) {
    						userid = rs.getInt(1);
    					}


    					fetchData.fetchData();
    					System.out.println("\n1.Add to cart\n2.Showcart\nSelect one of the above");
    					n=scanner.nextInt();

    					if(n==1 || n==2) {
    						switch(n) {
    						case 1:do {
    							fetchData.fetchData();

    							System.out.println("Enter above product id that you wish to buy");
    							int productid=scanner.nextInt();
    							System.out.println("Enter Quantity");
    							quantity=scanner.nextInt();
    							usd.userdata(productid, userid, quantity);

    							System.out.println("Do You Wish To Continue:enter Y for Yes or N for No");
    							ch=scanner.next();
    						}
    						while(ch.equalsIgnoreCase("Y"));
    						showcart.showCart(userid);
    						break;

    						case 2:
    							showcart=new ShowCart();
    							showcart.showCart(userid);

    							break;	
    						}
    					}
    					break;
    // For Registered Buyer					
    				case 2:
    					System.out.println("Enter FirstName");
    					firstname=scanner.next();
    					System.out.println("Enter Password");
    					password=scanner.next();

    					ps=connection.prepareStatement("select userId from user where firstname=? and password=?");
    					ps.setString(1 , firstname);
    					ps.setString(2 , password);
    					rs=ps.executeQuery();

    					while(rs.next()) {
    						userid = rs.getInt(1);
    					}
    // Checking if Buyer is already present or not					
    					if (userid != 0) {

    						fetchData.fetchData();
    						System.out.println("\n1.Add to cart\n2.showcart\nselect one of the above");
    						n=scanner.nextInt();
    // Giving Add to cart and Show cart options
    						if(n==1 || n==2) {
    							switch(n) {
    							case 1:do {
    								fetchData.fetchData();

    								System.out.println("Enter id");
    								int productid=scanner.nextInt();
    								System.out.println("Enter quantity");
    								quantity=scanner.nextInt();
    								usd.userdata(productid, userid, quantity);

    								System.out.println("\nDo You Wish To Continue:enter Y for Yes or N for No");
    								ch=scanner.next();
    							}
    							while(ch.equalsIgnoreCase("Y"));
    							showcart.showCart(userid);
    							while(ch.equalsIgnoreCase("N"));
    							System.out.println("***********Thank you visit again**************");
    							break;

    							case 2:
    								showcart=new ShowCart();
    								showcart.showCart(userid);

    								break;	
    							}
    						}

    					}else {
    						System.out.println("Invalid User");
    					}
    					break;
    				}
    				System.out.println("\nThank You So Much For Shopping With Us !");
    			}

    			System.out.println("\nDo You Wish To Continue:enter Y for Yes or N for No");
    			ch=scanner.next();
 
    		}
    		while(ch.equalsIgnoreCase("Y"));
    		if(ch.equalsIgnoreCase("N"))
    		{
    			System.out.println("**************Thank You Visit Again*********");
    		}
    	}

    
    }
   
	
	
	


