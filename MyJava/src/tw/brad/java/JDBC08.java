package tw.brad.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

public class JDBC08 {

	public static void main(String[] args) {
		String data = fetchOpendata();
		if (data != null) {
			toMySQL(data);
		}
		
	}

	static String fetchOpendata() {
		String ret = null;
		try {
			URL url = new URL("https://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvAgriculturalProduce.aspx");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.connect();
			
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line; StringBuffer sb = new StringBuffer();
			while ( (line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			
			ret = sb.toString();
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return ret;
	}
	
	static void toMySQL(String json) {
		try {
			String urlGife = "jdbc:mysql://localhost:3306/iii";
			Properties prop = new Properties();
			prop.put("user", "root");
			prop.put("password", "root");
			Connection conn = DriverManager.getConnection(urlGife, prop);
			
			String sql = "INSERT INTO gift (name,org,spec,url,addr)" +
					" VALUES (?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			JSONArray root = new JSONArray(json);
			for (int i=0; i<root.length(); i++) {
				JSONObject row = root.getJSONObject(i);
				String name = row.getString("Name");
				String org = row.getString("ProduceOrg");
				String spec = row.getString("SpecAndPrice");
				String url = row.getString("Column1");
				String addr = row.getString("SalePlace");
				
				pstmt.setString(1, name);
				pstmt.setString(2, org);
				pstmt.setString(3, spec);
				pstmt.setString(4, url);
				pstmt.setString(5, addr);
				pstmt.executeUpdate();
			}
			System.out.println("finish");
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		
	}
	
}
