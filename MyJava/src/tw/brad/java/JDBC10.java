package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC10 {

	public static void main(String[] args) {
		String account = "eric", passwd = "123456", realname = "Brad Chao";
		
		String url = "jdbc:mysql://localhost:3306/iii";
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		try(Connection conn = DriverManager.getConnection(url, prop)) {
			if (!isDataRepeat(account, conn)) {
				if (appendData(account, passwd, realname, conn)) {
					System.out.println("append ok");
				}else {
					System.out.println("append error");
				}
			}else {
				System.out.println("repeat");
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	static boolean isDataRepeat(String account, Connection conn)
		throws SQLException {
		String sql = "SELECT count(*) as count FROM member WHERE account = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, account);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		return rs.getInt("count") != 0;
	}
	
	static boolean appendData(
			String account, String passwd, String realname, Connection conn)
			throws SQLException {
		String sql = "INSERT INTO member (account, password, realname) VALUES (?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, account);
		pstmt.setString(2, passwd);
		pstmt.setString(3, realname);
		int rowCount = pstmt.executeUpdate();
		
		return rowCount != 0;
	}

}
