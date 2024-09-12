package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.helper.StudentHelper;
import entity.StudentDTO;
import utills.Generics;

public class StudentDAO {

	private PreparedStatement preparedstatement = null;
	private ResultSet resultSet = null;
	private Generics utills;
	private Connection connection = null;
	private StudentDTO studentDTO;
	
	public StudentDAO(Generics utills) {
		this.utills = utills;
	}

	public ArrayList<StudentDTO> getAllUserData() {
		ArrayList<StudentDTO> list = null;
		studentDTO = null;
		connection = null;
		preparedstatement = null;
		resultSet = null;
		try {
			connection = utills.getConnection();
			preparedstatement = connection.prepareStatement("SELECT * FROM USERS_TABLE WHERE ROLE = 'STUDENT';");
			resultSet = preparedstatement.executeQuery();
			list = StudentHelper.getAllUsersDataDTO(list, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedstatement, resultSet);
		}
		return list;
	}
	
	

	public StudentDTO getLogin(String email, String password) {
		studentDTO = null;
		connection = null;
		preparedstatement = null;
		resultSet = null;
		try {
			connection = utills.getConnection();
			preparedstatement = connection.prepareStatement("SELECT * FROM USERS_TABLE WHERE MEMBER_ID = ? && PASSWORD = ? ;");
			preparedstatement.setString(1, email);
			preparedstatement.setString(2, password);
			resultSet = preparedstatement.executeQuery();
			studentDTO = StudentHelper.getLoginDTO(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedstatement, resultSet);
		}
		return studentDTO;
	}
	
	

	public StudentDTO getSingleUser(String email) {
		studentDTO = null;
		connection = null;
		preparedstatement = null;
		resultSet = null;
		try {
			connection = utills.getConnection();
			preparedstatement = connection.prepareStatement("SELECT * FROM USERS_TABLE WHERE ROLE = 'STUDENT' && EMAIL = ?;");
			preparedstatement.setString(1, email);
			resultSet = preparedstatement.executeQuery();
			studentDTO = StudentHelper.getSingleUserDTO(resultSet, studentDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedstatement, resultSet);
		}
		return studentDTO;
	}
	
	

	public boolean registerUserData(StudentDTO studentDTO) {
		boolean a = false;
		connection = null;
		preparedstatement = null;
		try {
			connection = utills.getConnection();
			preparedstatement = connection.prepareStatement("INSERT INTO USERS_TABLE (email, name, password, role, member_id ) values (?,?,?,?,?);");
			a = StudentHelper.registerUserDataDTO(preparedstatement, studentDTO, a);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedstatement, resultSet);
		}
		return a;
	}
	
	

	public boolean deletUserData(String email) {
		connection = null;
		preparedstatement = null;
		boolean a = false;
		try {
			connection = utills.getConnection();
			preparedstatement = connection.prepareStatement("DELETE FROM USERS_TABLE WHERE ROLE = 'STUDENT' && EMAIL = ?;");
			preparedstatement.setString(1, email);
			a = preparedstatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			utills.closeConnection(connection, preparedstatement);
		}
		return a;
	}
}
