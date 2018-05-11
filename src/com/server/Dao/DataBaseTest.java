package com.server.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseTest {
	
	public static void main(String[] args) {        
	        Connection c = null;
	        try {
	            Class.forName("org.sqlite.JDBC");
	            c= DriverManager.getConnection("jdbc:sqlite:test.db");
	
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
	
	        System.out.println("Open databse successfully");
	    }
}
