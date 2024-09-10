 package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.helper.AdminHelper;
import dto.AdminDTO;
import utills.Generics;

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
			preparedStatement = connection.prepareStatement("SELECT * FROM USERS_TABLE WHERE MEMBER_ID = ? && PASSWORD = ? && ROLE = 'ADMIN';");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			adminDTO = AdminHelper.getAdminLoginDTO(adminDTO, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement, resultSet);
		}
		return adminDTO;
	}
	
	public static void main(String[] args) {
		new AdminDAO(new Generics()).getAdminLogin("VLMS2024ADMIN09100116", "Somesh@1234").getEmail();
	}

	public AdminDTO getSingleAdminUser(String email) {
		AdminDTO adminDTO = null;
		try {
			connection = utills.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM USERS_TABLE WHERE EMAIL = ?  && ROLE = 'ADMIN';");
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			adminDTO = AdminHelper.getSingleAdminUserDTO(adminDTO, resultSet);
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
			preparedStatement = connection.prepareStatement("INSERT INTO USERS_TABLE (email, name, password, role, name_of_library, address, member_id) values (?,?,?,?,?,?,?);");
			a = AdminHelper.registerAdminDataDTO(preparedStatement, adminDTO, a);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedStatement);
		}
		return a;
	}
	
	

}
