package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.StudentDTO;
import utills.Generics;
import utills.MembershipNoGenerator;

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
			preparedstatement = connection.prepareStatement("SELECT * FROM STUDENT;");
			resultSet = preparedstatement.executeQuery();
			list = new ArrayList<>();
			while (resultSet.next()) {
				studentDTO = new StudentDTO();
				studentDTO.setEmail(resultSet.getString("email"));
				studentDTO.setName(resultSet.getString("name"));
				studentDTO.setId(resultSet.getInt("id"));
				studentDTO.setMembership_no(resultSet.getString("student_member_id"));
				list.add(studentDTO);
			}
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
			preparedstatement = connection.prepareStatement("SELECT * FROM STUDENT WHERE EMAIL = ? && PASSWORD = ? ;");
			preparedstatement.setString(1, email);
			preparedstatement.setString(2, password);
			resultSet = preparedstatement.executeQuery();
			while (resultSet.next()) {
				studentDTO = new StudentDTO();
				studentDTO.setId(resultSet.getInt("id"));
				studentDTO.setEmail(resultSet.getString("email"));
				studentDTO.setName(resultSet.getString("name"));
				studentDTO.setRole(resultSet.getInt("role"));
				studentDTO.setMembership_no(resultSet.getString("student_member_id"));
			}
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
			preparedstatement = connection.prepareStatement("SELECT * FROM STUDENT WHERE EMAIL = ?;");
			preparedstatement.setString(1, email);
			resultSet = preparedstatement.executeQuery();
			if (resultSet!=null) {
				while (resultSet.next()) {
					studentDTO = new StudentDTO();
					studentDTO.setId(resultSet.getInt("id"));
					studentDTO.setEmail(resultSet.getString("email"));
					studentDTO.setName(resultSet.getString("name"));
					studentDTO.setPassword(resultSet.getString("password"));
					studentDTO.setMembership_no(resultSet.getString("student_member_id"));
				}
			}
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
			preparedstatement = connection.prepareStatement("INSERT INTO STUDENT (email, name, password, role, student_member_id ) values (?,?,?,?,?);");
			preparedstatement.setString(1, studentDTO.getEmail());
			preparedstatement.setString(2, studentDTO.getName());
			preparedstatement.setString(3, studentDTO.getPassword());
			preparedstatement.setInt(4, studentDTO.getRole());
			preparedstatement.setString(5, MembershipNoGenerator.getMembershipNo("Student"));
			a = preparedstatement.execute();
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
			preparedstatement = connection.prepareStatement("DELETE FROM STUDENT WHERE EMAIL = ?;");
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
