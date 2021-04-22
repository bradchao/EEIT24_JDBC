package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.json.JSONStringer;
import org.json.JSONWriter;

public class JDBC09 {

	public static void main(String[] args) {
		String sql;
		try {
			String urlGife = "jdbc:mysql://localhost:3306/iii";
			Properties prop = new Properties();
			prop.put("user", "root");
			prop.put("password", "root");
			Connection conn = DriverManager.getConnection(urlGife, prop);

			sql = "SELECT count(*) as nums from gift";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int nums = rs.getInt("nums");
			//System.out.println(nums);
			
			int rpp  = 4;
			int page = 3;
			int start = (page -1)*rpp;
			
			sql = String.format("SELECT * FROM gift LIMIT %d, %d", start, rpp);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			JSONStringer js = new JSONStringer();
			JSONWriter jw = js.array();
			
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				
				//System.out.println(id + ":" + name);
				jw.object();
					jw.key("id").value(id);
					jw.key("name").value(name);
				jw.endObject();
			}
			jw.endArray();
			
			System.out.println(jw);
			
			
			
		}catch(Exception e) {
			
		}
	}

}
