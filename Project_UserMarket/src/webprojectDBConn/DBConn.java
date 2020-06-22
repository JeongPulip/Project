package webprojectDBConn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConn {
	
		public static Connection getConnection() {
			
			Connection conn = null;
			try {
				Context context = new InitialContext();
				DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/orcl");
				conn = dataSource.getConnection();
			} catch (NamingException e) {
				System.out.println("DBConn 오류1 : NamingException");
				e.printStackTrace();
			}catch (SQLException e) {
				System.out.println("DBConn 오류2 : SQLException");
				e.printStackTrace();
			} 
	         return conn;
		}
		
}
