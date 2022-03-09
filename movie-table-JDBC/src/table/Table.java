package table;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Table {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public Table(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		
		this.conn = conn;
		this.pstmt = pstmt;
		this.rs = rs;
	}
	
	public void createTable() throws IOException, SQLException {	// Movie Table 생성
		
		BufferedReader tableReader = new BufferedReader(new FileReader("D:\\database_java\\create_table.txt"));
		StringBuilder createTableSQL = new StringBuilder();
		
		String str = "";
		while((str = tableReader.readLine()) != null) {
			createTableSQL.append(str + "\n");
		}
		tableReader.close();
		
		pstmt = conn.prepareStatement(createTableSQL.toString());
		pstmt.executeUpdate();
		
	}
	
	public void insertData() throws SQLException, Exception {
		
		BufferedReader dataReader = new BufferedReader(new FileReader("D:\\database_java\\movie_data.txt"));
		String dataString = "";
		
		String insertSQL = "insert into movie (id, title, company, releasedate, country, totalscreen, profit, totalnum, grade) values (?,?,?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(insertSQL);
		
		while((dataString = dataReader.readLine()) != null) {
			String[] dataArr = dataString.split("\\|");
			
			pstmt.setString(1, dataArr[1]);
			pstmt.setString(2, dataArr[2]);
			pstmt.setString(3, dataArr[3]);
			pstmt.setString(4, dataArr[4]);
			pstmt.setString(5, dataArr[5]);
			pstmt.setInt(6, Integer.valueOf(dataArr[6]));
			pstmt.setDouble(7, Double.valueOf(dataArr[7]));
			pstmt.setInt(8, Integer.valueOf(dataArr[8]));
			pstmt.setString(9, dataArr[9]);
			pstmt.addBatch();
		}
		
		dataReader.close();
		pstmt.executeBatch();
	}
	
	public void searchData(String sql) throws SQLException {
		
		boolean isExist = false;
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		StringBuffer buf = new StringBuffer();
		
		while(rs.next()) {
			buf.append("|");
			buf.append(rs.getString("id"));
			buf.append("|");
			buf.append(rs.getString("title"));
			buf.append("|");
			buf.append(rs.getString("company"));
			buf.append("|");
			buf.append(rs.getDate("releasedate"));
			buf.append("|");
			buf.append(rs.getString("country"));
			buf.append("|");
			buf.append(rs.getInt("totalscreen"));
			buf.append("|");
			buf.append(rs.getDouble("profit"));
			buf.append("|");
			buf.append(rs.getInt("totalnum"));
			buf.append("|");
			buf.append(rs.getString("grade"));
			buf.append("|\n");
			isExist = true;
			}
		
		if(isExist == true) {
			System.out.println(buf);
		}
		else {
			System.out.println("검색 결과가 없습니다.");
		}
		
	}
	
	
	
}
