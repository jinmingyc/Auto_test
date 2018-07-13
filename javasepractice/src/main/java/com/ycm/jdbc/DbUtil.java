package com.ycm.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String createDatabase = "CREATE DATABASE jdbc_db";
		// createDatabase(createDatabase);
		String createTable = "CREATE TABLE student " + "(id INTEGER not NULL, " + " first VARCHAR(255), "
				+ " last VARCHAR(255), " + " age INTEGER, " + " PRIMARY KEY ( id ))";

		CreateTable(createTable);
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			// "oracle.jdbc.driver.OracleDriver"
			String className = "com.mysql.jdbc.Driver";
			Class.forName(className);
			/*
			 * jdbc:mysql://localhost:3306/RUNOOB root 888888
			 */
			// String url = "jdbc:oracle:thin:@172.26.14.231:1521:ORCL";
			// String user = "ZTC456";
			// String password = "ZTC456";
			// jdbc:mysql://localhost/
			String url = "jdbc:mysql://localhost/jdbc_db?useSSL=false";
			String user = "root";
			String password = "888888";
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static void createDatabase(String querySql) {
		System.out.println("使用statement的方式查询");
		Connection conn = getConnection();
		String sql = querySql;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			/*
			 * while (rs.next()) { int id = rs.getInt("id"); String FLEX_VALUE_SET_TYPE =
			 * rs.getString("FLEX_VALUE_SET_TYPE"); System.out.println(id);
			 * System.out.println(FLEX_VALUE_SET_TYPE);
			 */
			// }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		System.out.println("创建数据库完成");
	}

	private static void CreateTable(String createTable) {
		// TODO Auto-generated method stub
		System.out.println("创建表");
		Connection conn = getConnection();
		String sql = createTable;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			/*
			 * while (rs.next()) { int id = rs.getInt("id"); String FLEX_VALUE_SET_TYPE =
			 * rs.getString("FLEX_VALUE_SET_TYPE"); System.out.println(id);
			 * System.out.println(FLEX_VALUE_SET_TYPE);
			 */
			// }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("创建表完成");
	}

}
