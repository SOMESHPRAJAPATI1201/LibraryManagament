package utills;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Generics {

	private String URL = "jdbc:mysql://localhost:3306/librarymanagementsystem";
	private String ID = "root";
	private String PASSWORD = "root";
	private Connection conn = null;

	public Connection getConnection() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(URL, ID, PASSWORD);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return conn;
	}

	public void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection(Connection conn, PreparedStatement pstmt) {
		closeConnection(conn, pstmt, null);
	}
}
