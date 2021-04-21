package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBC07 {

	public static void main(String[] args) {
		
		try {
			String url = "jdbc:mysql://localhost:3306/iii";
			Properties prop = new Properties();
			prop.put("user", "root");
			prop.put("password", "root");
			Connection conn = DriverManager.getConnection(url, prop);

			String sql = "INSERT INTO cust (cname,birthday,tel)" +
					" VALUES (?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			for (int i=0; i<10; i++) {
				pstmt.setString(1, "Brad" + (int)(Math.random()*49+1));
				pstmt.setString(2, "2020-01-02");
				pstmt.setString(3, "1234567");
				pstmt.executeUpdate();
			}
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}

}
