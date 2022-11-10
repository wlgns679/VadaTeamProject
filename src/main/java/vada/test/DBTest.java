package vada.test;

import java.sql.Connection;
import java.sql.SQLException;

import vada.util.DB;
 
public class DBTest {
	public static void main(String[] args) {
 
		Connection conn = null;
		try {
			conn = DB.getConnection();
			if (conn != null) {
				System.out.println(conn);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException sqle) {
			}
		}
	}
}// DBTest
