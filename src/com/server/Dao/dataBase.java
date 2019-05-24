package com.server.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import sun.security.jca.GetInstance;

public class dataBase {
	private String dbName = "jdbc:sqlite:test.db";
	private Connection c = null;
	private PreparedStatement pstmt = null;
	private static dataBase instance = new dataBase();
	private dataBase() {
		initConnection();
	}
	
	
	/*
	 * todo:用actioncell做回调.
	 * 多线程.
	 * 阻塞队列
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	public static synchronized dataBase GetInstance() {
		return instance;
	}
	
	
	public void initConnection() {
		try { 
			Class.forName("org.sqlite.JDBC");
	        c = DriverManager.getConnection(dbName);			
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void close() {
		if (null != c) {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void refreshConnect() throws SQLException {
		if (null == c) {
			initConnection();
			return;
		}
		if (null != c && c.isClosed()) {
			initConnection();
		}
		
	}
	
	public Connection getConn() {
		try {
			refreshConnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	
	protected ResultSet executeQuerySQL(String sql, Object[] params) throws SQLException {
         ResultSet rs = null;
         Connection conn = getConn();
         if (null == conn) {
        	 return null;
			
		}
         pstmt = conn.prepareStatement(sql);
          if (params != null) {
              for (int i = 0; i < params.length; i++) {
                  pstmt.setObject(i + 1, params[i]);
              }
          }
          rs = pstmt.executeQuery();
          return rs;
    }
	
		  
		 
	
	public static void main(String[] args) {   
	        
	
	    }
}
