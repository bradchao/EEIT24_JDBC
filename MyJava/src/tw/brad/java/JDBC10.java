package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC10 {
	static final String sqlCheckRepeat = "SELECT count(*) as count FROM member WHERE account = ?";
	static final String sqlAppend = "INSERT INTO member (account, password, realname) VALUES (?,?,?)";
	static PreparedStatement pstmtCheckRepeat, pstmtAppend;

	public static void main(String[] args) {
		String account = "amy", passwd = "123456", realname = "Brad Chao";
		
		String url = "jdbc:mysql://localhost:3306/iii";
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		try(Connection conn = DriverManager.getConnection(url, prop)) {
			pstmtCheckRepeat = conn.prepareStatement(sqlCheckRepeat);
			pstmtAppend = conn.prepareStatement(sqlAppend);
			
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
		pstmtCheckRepeat.setString(1, account);
		ResultSet rs = pstmtCheckRepeat.executeQuery();
		rs.next();
		return rs.getInt("count") != 0;
	}
	
	static boolean appendData(
			String account, String passwd, String realname, Connection conn)
			throws SQLException {
		pstmtAppend.setString(1, account);
		pstmtAppend.setString(2, passwd);
		pstmtAppend.setString(3, realname);
		int rowCount = pstmtAppend.executeUpdate();
		
		return rowCount != 0;
	}

}
