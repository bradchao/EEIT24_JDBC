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
		String account = "amy", passwd = "12346";
		
		String url = "jdbc:mysql://localhost:3306/iii";
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		try(Connection conn = DriverManager.getConnection(url, prop)) {
			if ((member = login(account, passwd, conn)) != null) {
				System.out.println(
						String.format("Welcome, %s", member.getRealname()));
			}else {
				System.out.println("get out");;
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	static Member login(String account, String passwd, Connection conn) 
		throws Exception {
		member = null;
		String sql = "SELECT * FROM member WHERE account = ? AND password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, account);
		pstmt.setString(2, passwd);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			member = new Member(
					rs.getInt("id"), 
					rs.getString("account"), 
					rs.getString("realname"));
		}
		
		return member;
	}
	
}
