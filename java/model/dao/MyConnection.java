package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection{

	public static Connection getConnection() throws Exception{
	
    Class.forName("com.mysql.cj.jdbc.Driver");
	String url = "jdbc:mysql://localhost:3306/management_db";
    String user = "manager";
    String password = "manager";
    
    return DriverManager.getConnection(url, user, password);
	}
}
