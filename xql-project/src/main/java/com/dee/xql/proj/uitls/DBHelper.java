package com.dee.xql.proj.uitls;

import java.sql.*;

import com.dee.xql.proj.config.DBConfig;

public class DBHelper {

	public static Connection getConn() throws ClassNotFoundException, SQLException {
		DBConfig dbConfig = BeanHelper.getBean(DBConfig.class);
		Class.forName(dbConfig.getDriverClassName());
		return DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
	}

	public static void closeDB(Connection conn, ResultSet rs, PreparedStatement pstmt, CallableStatement proc) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (proc != null) {
			try {
				proc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
