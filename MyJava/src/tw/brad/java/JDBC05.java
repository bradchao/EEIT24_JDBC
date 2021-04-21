package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBC05 {

	public static void main(String[] args) {
		
		try {
			String url = "jdbc:mysql://localhost:3306/iii";
			Properties prop = new Properties();
			prop.put("user", "root");
			prop.put("password", "root");
			Connection conn = DriverManager.getConnection(url, prop);

			Statement stmt = conn.createStatement();
			ResultSet rs  = stmt.executeQuery("SELECT * FROM `iii`.`cust`");

			int indexId = rs.findColumn("id");
			int indexCname = rs.findColumn("cname");
			int indexTel = rs.findColumn("tel");
			int indexBirthday = rs.findColumn("birthday");

			while (rs.next()) {
				String id = rs.getString(indexId);
				String cname = rs.getString(indexCname);
				String tel = rs.getString(indexTel);
				String birthday = rs.getString(indexBirthday);
				System.out.println(id+":"+cname+":"+tel+":"+birthday);
			}
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}

}
