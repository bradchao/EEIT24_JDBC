package tw.brad.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBC14 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/iii";
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		try(Connection conn = DriverManager.getConnection(url, prop)) {
			FileInputStream fin = new FileInputStream("img/coffee.jpg");
			
			String sql = "SELECT * FROM member WHERE id = 1";
			PreparedStatement pstmt =  conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			FileOutputStream fout = new FileOutputStream("img/newcoffee.jpg");
			InputStream in = rs.getBinaryStream("img");
			int len = 0; byte[] buf = new byte[4096];
			while ( (len = in.read(buf)) != -1) {
				fout.write(buf, 0, len);
			}
			fout.flush();
			fout.close();
			in.close();
			
			InputStream in2 = rs.getBinaryStream("obj");
			ObjectInputStream oin = new ObjectInputStream(in2);
			Student s1 = (Student)oin.readObject();
			System.out.println(s1.score());
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}


