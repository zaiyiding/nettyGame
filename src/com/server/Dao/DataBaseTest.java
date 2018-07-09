package com.server.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dataBaseTest {
	
	public static void main(String[] args) {   
	        try { 
	            Class.forName("org.sqlite.JDBC");
	            Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
	
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
	
	        System.out.println("Open databse successfully");
	    }
}
