package tw.brad.java;

import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBC13 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/iii";
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		Student s1 = new Student(100, 90, 74);
		System.out.println(s1.score());
		
		try(Connection conn = DriverManager.getConnection(url, prop)) {
			FileInputStream fin = new FileInputStream("img/coffee.jpg");
			
			String sql = "UPDATE member SET img=?,obj=? WHERE id = 1";
			PreparedStatement pstmt =  conn.prepareStatement(sql);
			
			pstmt.setBinaryStream(1, fin);
			pstmt.setObject(2, s1);
			
			pstmt.executeUpdate();
			System.out.println("Update OK");
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}

class Student implements Serializable{
	private int ch, eng, math;
	Student(int ch, int eng, int math){
		this.ch = ch; this.eng = eng; this.math = math;
	}
	int score() {
		return ch + eng + math;
	}
}

