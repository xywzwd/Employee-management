package cn.yuwei.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import cn.yuwei.jdbc.Test;

public class DBUtils {

	static String driver = "";
	
	static {
		InputStream is = DBUtils.class.getResourceAsStream("/conn.properties");
		Properties p = new Properties();
		try {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    driver = p.getProperty("driver");
	}
	
	public static Connection getConnection() {
	
		
		Connection conn=null;
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
	
	public static void closeAll() {
		
	}
}
