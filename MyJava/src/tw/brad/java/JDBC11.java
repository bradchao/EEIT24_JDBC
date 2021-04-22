package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC11 {
	static Member member;
	public static void main(String[] args) {
		String account = "amy", passwd = "123456";
		
		String url = "jdbc:mysql://localhost:3306/iii";
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		try(Connection conn = DriverManager.getConnection(url, prop)) {
			if (login(account, passwd, conn)) {
				System.out.println(String.format("Welcome, %s", member.getRealname()));
			}else {
				System.out.println("get out");;
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	static boolean login(String account, String passwd, Connection conn) {
		return true;
	}
	
}
