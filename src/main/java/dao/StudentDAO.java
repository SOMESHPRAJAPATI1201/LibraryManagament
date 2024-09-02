package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.StudentDTO;
import utills.Generics;

public class StudentDAO {

	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Generics utills;
	private Connection conn = null;
	
	public StudentDAO(Generics utills) {
		this.utills = utills;
	}

	public ArrayList<StudentDTO> getAllUserData() {
		ArrayList<StudentDTO> list = new ArrayList<>();
		try {
			StudentDTO studentDTO = null;
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM STUDENT;");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				studentDTO = new StudentDTO();
				studentDTO.setEmail(rs.getString("email"));
				studentDTO.setName(rs.getString("name"));
				list.add(studentDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return list;
	}

	public StudentDTO getLogin(String email, String password) {
		StudentDTO studentDTO = null;
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM STUDENT WHERE EMAIL = ? && PASSWORD = ? ;");
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				studentDTO = new StudentDTO();
				studentDTO.setId(rs.getInt("id"));
				studentDTO.setEmail(rs.getString("email"));
				studentDTO.setName(rs.getString("name"));
				studentDTO.setRole(rs.getInt("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return studentDTO;
	}

	public StudentDTO getSingleUser(String email) {
		StudentDTO studentDTO = null;
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM STUDENT WHERE EMAIL = ?;");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					studentDTO = new StudentDTO();
					studentDTO.setId(rs.getInt("id"));
					studentDTO.setEmail(rs.getString("email"));
					studentDTO.setName(rs.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return studentDTO;
	}

	public boolean registerUserData(StudentDTO studentDTO) {
		boolean a = false;
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO STUDENT (email, name, password, role) values (?,?,?,?);");
			pstmt.setString(1, studentDTO.getEmail());
			pstmt.setString(2, studentDTO.getName());
			pstmt.setString(3, studentDTO.getPassword());
			pstmt.setInt(4, studentDTO.getRole());
			a = pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return a;
	}

	public boolean deletUserData(String email) {
		boolean a = false;
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM STUDENT WHERE EMAIL = ?;");
			pstmt.setString(1, email);
			a = pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt);
		}
		return a;
	}
}
