package vada.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vada.constants.DBConnPoolConstants;
import vada.constants.VADAConstants;

public class ConnectionManager {

	public static Connection getConnection() {
		String JDBC_URL = (String)DBConnPoolConstants.props.get("JDBC_POOLING_URL");
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(JDBC_URL);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	} // getConnection()

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public static void closeConnection(Statement stmt, Connection conn) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		closeConnection(conn);
	}

	public static void closeConnection(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		closeConnection(stmt, conn);
	}
	
	public static void closeConnection(ResultSet rs, Statement stmt) {
		if (rs != null) {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}


} // class






