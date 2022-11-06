package com.miniproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass 
{
public static Connection getConnection() {
	Connection connection=null;
	//step 1:load a driver
	try {
		Class.forName("com.mysql.jdbc.Driver");
		
		//step 2: establish connection
		String url="jdbc:mysql://localhost:3306/ecom?characterEncoding=utf8";
		 connection=DriverManager.getConnection(url, "root", "hemalata");
		 
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return connection;
	
}
}
