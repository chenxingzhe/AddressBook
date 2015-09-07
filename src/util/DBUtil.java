package util;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * mysql数据库连接类
 * @author Jasper
 *
 */
public class DBUtil {
	
	/**
	 *获取数据库连接
	 *
	 * @return 一个数据库连接 
	 */
	public static Connection getConnection(){
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/addressbook?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "";
		
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
