package com.miniproject;


	
	
		
		import java.sql.SQLException;
		import java.util.Scanner;

		public class User {

			public static void main(String[] args) throws SQLException {
				Scanner scanner=new Scanner(System.in);
				
				RegisterInfo ri=new RegisterInfo();
				System.out.println("Enter your Firstname>>>");
				String firstname=scanner.next();
				
			    System.out.println("Enter your Lastname>>>");
		        String lastname=scanner.next();
		          
				System.out.println("Enter the Password>>>");
				String password=scanner.next();
				
		      ri.registerInsert(firstname, lastname, password); 


		     UserDataInsert usd=new UserDataInsert();
		     ShowCart showcart=new ShowCart();
		     FetchData fetchData=new FetchData();
		     fetchData.fetchData();
		     System.out.println("1.add to cart\n2.showcart\nselect one of the above");
		     int n=scanner.nextInt();
		     String ch="";
		     if(n==1 || n==2) {
			   switch(n) {
			case 1:do {
				fetchData.fetchData();
				
				System.out.println("enter id");
				int productid=scanner.nextInt();
				int userid=1;
				System.out.println("enter quantity");
				int quantity=scanner.nextInt();
				usd.userdata(productid, userid, quantity);
				
				System.out.println("Do You Wish To Continue:enter Y for Yes or N for No");
			 ch=scanner.next();
			}
			while(ch.equalsIgnoreCase("Y"));
			   showcart.showCart(1);
			   break;
			   
			case 2:
				  showcart=new ShowCart();
				    showcart.showCart(1);

				break;
			}
			}





		System.out.println("Enter user id");
		int userid=scanner.nextInt();
		 
				
				
		}

			}

}
