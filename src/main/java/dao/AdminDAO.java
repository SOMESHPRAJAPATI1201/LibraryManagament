package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.AdminDTO;
import utills.Generics;
import utills.MembershipNoGenerator;

public class AdminDAO {

	private PreparedStatement pstmt = null;
	private ResultSet resultSet = null;
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
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				adminDTO = new AdminDTO();
				adminDTO.setEmail(resultSet.getString("email"));
				adminDTO.setName(resultSet.getString("name"));
				adminDTO.setId(resultSet.getInt("id"));
				adminDTO.setPassword(resultSet.getString("password"));
				adminDTO.setLibName(resultSet.getString("name_of_library"));
				adminDTO.setRole(resultSet.getInt("role"));
				adminDTO.setAddress(resultSet.getString("address"));
				adminDTO.setMembership_no(resultSet.getString("admin_membr_id"));
				list.add(adminDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, resultSet);
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
			resultSet = pstmt.executeQuery();
			if (resultSet!=null) {
				while (resultSet.next()) {
					adminDTO = new AdminDTO();
					adminDTO.setEmail(resultSet.getString("email"));
					adminDTO.setName(resultSet.getString("name"));
					adminDTO.setId(resultSet.getInt("id"));
					adminDTO.setPassword(resultSet.getString("password"));
					adminDTO.setLibName(resultSet.getString("name_of_library"));
					adminDTO.setRole(resultSet.getInt("role"));
					adminDTO.setAddress(resultSet.getString("address"));
					adminDTO.setMembership_no(resultSet.getString("admin_membr_id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, resultSet);
		}
		return adminDTO;
	}

	public AdminDTO getSingleAdminUser(String email) {
		AdminDTO adminDTO = null;
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM ADMIN WHERE EMAIL = ?;");
			pstmt.setString(1, email);
			resultSet = pstmt.executeQuery();
			if (resultSet!=null) {
				while (resultSet.next()) {
					adminDTO = new AdminDTO();
					adminDTO.setEmail(resultSet.getString("email"));
					adminDTO.setName(resultSet.getString("name"));
					adminDTO.setId(resultSet.getInt("id"));
					adminDTO.setPassword(resultSet.getString("password"));
					adminDTO.setLibName(resultSet.getString("name_of_library"));
					adminDTO.setRole(resultSet.getInt("role"));
					adminDTO.setAddress(resultSet.getString("address"));
					adminDTO.setMembership_no(resultSet.getString("admin_membr_id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(conn, pstmt, resultSet);
		}
		return adminDTO;
	}

	public int registerAdminData(AdminDTO adminDTO) {
		int a = 0;
		try {
			conn = utills.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO ADMIN (email, name, password, role, name_of_library, address, admin_membr_id) values (?,?,?,?,?,?,?);");
			pstmt.setString(1, adminDTO.getEmail());
			pstmt.setString(2, adminDTO.getName());
			pstmt.setString(3, adminDTO.getPassword());
			pstmt.setInt(4, adminDTO.getRole());
			pstmt.setString(5, adminDTO.getLibName());
			pstmt.setString(6, adminDTO.getAddress());
			pstmt.setString(7, MembershipNoGenerator.getMembershipNo("admin"));
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
