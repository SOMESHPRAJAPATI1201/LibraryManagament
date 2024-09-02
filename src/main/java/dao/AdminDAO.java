package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.AdminDTO;
import utills.Generics;

public class AdminDAO {

	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Generics utills;
	private Connection conn = null;

	public AdminDAO(Generics utills) {
		this.utills = utills;
	}

	public ArrayList<AdminDTO> getAllAdminData() {
		ArrayList<AdminDTO> list = new ArrayList<>();
		try {
			AdminDTO adminDTO = null;
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM ADMIN;");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				adminDTO = new AdminDTO();
				adminDTO.setEmail(rs.getString("email"));
				adminDTO.setName(rs.getString("name"));
				adminDTO.setId(rs.getInt("id"));
				adminDTO.setPassword(rs.getString("password"));
				adminDTO.setLibName(rs.getString("name_of_library"));
				adminDTO.setRole(rs.getInt("role"));
				adminDTO.setAddress(rs.getString("address"));
				list.add(adminDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return list;
	}

	public AdminDTO getAdminLogin(String email, String password) {
		AdminDTO adminDTO = null;
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM ADMIN WHERE EMAIL = ? && PASSWORD = ?;");
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					adminDTO = new AdminDTO();
					adminDTO.setEmail(rs.getString("email"));
					adminDTO.setName(rs.getString("name"));
					adminDTO.setId(rs.getInt("id"));
					adminDTO.setPassword(rs.getString("password"));
					adminDTO.setLibName(rs.getString("name_of_library"));
					adminDTO.setRole(rs.getInt("role"));
					adminDTO.setAddress(rs.getString("address"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return adminDTO;
	}

	public AdminDTO getSingleAdminUser(String email) {
		AdminDTO adminDTO = null;
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM ADMIN WHERE EMAIL = ?;");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					adminDTO = new AdminDTO();
					adminDTO.setEmail(rs.getString("email"));
					adminDTO.setName(rs.getString("name"));
					adminDTO.setId(rs.getInt("id"));
					adminDTO.setPassword(rs.getString("password"));
					adminDTO.setLibName(rs.getString("name_of_library"));
					adminDTO.setRole(rs.getInt("role"));
					adminDTO.setAddress(rs.getString("address"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, rs);
		}
		return adminDTO;
	}

	public int registerAdminData(AdminDTO adminDTO) {
		int a = 0;
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO ADMIN (email, name, password, role, name_of_library, address) values (?,?,?,?,?,?);");
			pstmt.setString(1, adminDTO.getEmail());
			pstmt.setString(2, adminDTO.getName());
			pstmt.setString(3, adminDTO.getPassword());
			pstmt.setInt(4, adminDTO.getRole());
			pstmt.setString(5, adminDTO.getLibName());
			pstmt.setString(6, adminDTO.getAddress());
			a = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt);
		}
		return a;
	}

	public boolean deletAdminData(String email) {
		boolean a = false;
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM ADMIN WHERE EMAIL = ?;");
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
