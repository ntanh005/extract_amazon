package com.ntanh.extract;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	private static MyConnection instance;
	Connection con = null;
	private MyConnection(){		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/api", "root", "123456@Ab");
		}catch(Exception ex){
			
		}
	}
	public static MyConnection getInstance(){
		if(instance == null){
			instance =  new MyConnection();
		}
		
		return instance;
	}
	
	public  Connection getConection(){
		return con;
	}
	public static void close(){
		try {
			instance.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
