package tw.brad.java;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBC12 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/iii";
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		try(Connection conn = DriverManager.getConnection(url, prop)) {
			DatabaseMetaData metadata = conn.getMetaData();
			boolean isSupport = metadata.supportsResultSetConcurrency(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_UPDATABLE);
			System.out.println(isSupport);
			
			String sql = "SELECT * FROM member WHERE id = 2";
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			System.out.println(rs.getString("account")+":" + rs.getString("realname"));
		
			rs.updateString("realname", "Eric Chen");
			rs.updateRow();
			
			//rs.deleteRow();

//			rs.moveToInsertRow();
//			rs.updateString("realname", "xxx");
//			rs.insertRow();

//			rs.beforeFirst();
//			while (rs.next()) {
//				rs.deleteRow();
//			}
			
			
		}catch(Exception e) {
			
		}
	}

}
