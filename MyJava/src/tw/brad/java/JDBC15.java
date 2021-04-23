package tw.brad.java;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JDBC15 extends JFrame{
	private ResultSet rs;
	private int dataCount;
	
	public JDBC15() {
		super();
		setLayout(new BorderLayout());
		
		initDB();
		
		MyTableModle modle = new MyTableModle();
		JTable jtable = new JTable(modle);
		
		JScrollPane jsp = new JScrollPane(jtable);
		
		add(jsp, BorderLayout.CENTER);
		
		setSize(640, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initDB() {
		String url = "jdbc:mysql://localhost:3306/iii";
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		try {
			Connection conn = DriverManager.getConnection(url, prop);
			String sqlCount = "SELECT count(*) as count FROM gift";
			PreparedStatement pstmtCount = conn.prepareStatement(sqlCount);
			ResultSet rs0 = pstmtCount.executeQuery();
			rs0.next();
			dataCount = rs0.getInt("count");
			
			String sql = "SELECT * FROM gift";
			PreparedStatement pstmt = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			
//			rs.absolute(2);
//			System.out.println(rs.getString(2));
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	private String[] fields = {"id", "name", "org", "spec"};
	
	private class MyTableModle extends DefaultTableModel {

		@Override
		public int getRowCount() {
			return dataCount;
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public String getColumnName(int column) {
			return fields[column];
		}

		@Override
		public Object getValueAt(int row, int column) {
			try{
				rs.absolute(row + 1);
				return rs.getString(column + 1);
			}catch(Exception e) {
				System.out.println(e);
				return "-----";
			}
		}
		
	}
	
	public static void main(String[] args) {
		new JDBC15();
	}

}
