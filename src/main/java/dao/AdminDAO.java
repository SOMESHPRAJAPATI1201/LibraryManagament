package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dto.AdminDTO;
import utills.Generics;
import utills.MembershipNoGenerator;

public class AdminDAO {

	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private Generics utills;
	private Connection connection = null;

	public AdminDAO(Generics utills) {
		this.utills = utills;
	}

	public AdminDTO getAdminLogin(String email, String password) {
		AdminDTO adminDTO = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM ADMIN WHERE EMAIL = ? && PASSWORD = ?;");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
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
			utills.closeConnection(connection, preparedStatement, resultSet);
		}
		return adminDTO;
	}

	public AdminDTO getSingleAdminUser(String email) {
		AdminDTO adminDTO = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM ADMIN WHERE EMAIL = ?;");
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
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
			utills.closeConnection(connection, preparedStatement, resultSet);
		}
		return adminDTO;
	}

	public int registerAdminData(AdminDTO adminDTO) {
		int a = 0;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO ADMIN (email, name, password, role, name_of_library, address, admin_membr_id) values (?,?,?,?,?,?,?);");
			preparedStatement.setString(1, adminDTO.getEmail());
			preparedStatement.setString(2, adminDTO.getName());
			preparedStatement.setString(3, adminDTO.getPassword());
			preparedStatement.setInt(4, adminDTO.getRole());
			preparedStatement.setString(5, adminDTO.getLibName());
			preparedStatement.setString(6, adminDTO.getAddress());
			preparedStatement.setString(7, MembershipNoGenerator.getMembershipNo("admin"));
			a = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement);
		}
		return a;
	}

}
