package vada.util;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
  
public class DB {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://172.16.11.8:3306/vadadb?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false",
				"vada",
				"1234"
			);
		
	}
}