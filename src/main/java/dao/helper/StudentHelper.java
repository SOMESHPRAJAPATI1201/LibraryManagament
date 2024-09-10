package dao.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.StudentDTO;
import utills.MembershipNoGenerator;
import utills.ServicesHelper;

public class StudentHelper {
	
	public static ArrayList<StudentDTO> getAllUsersDataDTO(ArrayList<StudentDTO> list,ResultSet resultSet) throws SQLException {
		list = new ArrayList<>();
		StudentDTO studentDTO = null;
		while (resultSet.next()) {
			studentDTO = new StudentDTO();
			studentDTO.setEmail(resultSet.getString("email"));
			studentDTO.setName(resultSet.getString("name"));
			studentDTO.setId(resultSet.getInt("id"));
			studentDTO.setMembership_no(resultSet.getString("member_id"));
			list.add(studentDTO);
		}
		return list;
	}
	
	public static StudentDTO getLoginDTO(ResultSet resultSet) throws SQLException {
		StudentDTO studentDTO = null;
		while (resultSet.next()) {
			studentDTO = new StudentDTO();
			studentDTO.setId(resultSet.getInt("id"));
			studentDTO.setEmail(resultSet.getString("email"));
			studentDTO.setName(resultSet.getString("name"));
			studentDTO.setRole(resultSet.getString("role"));
			studentDTO.setMembership_no(resultSet.getString("member_id"));
		}
		return studentDTO;
	}
	
	public static StudentDTO getSingleUserDTO(ResultSet resultSet, StudentDTO studentDTO) throws SQLException {
		if (resultSet!=null) {
			while (resultSet.next()) {
				studentDTO = new StudentDTO();
				studentDTO.setId(resultSet.getInt("id"));
				studentDTO.setEmail(resultSet.getString("email"));
				studentDTO.setName(resultSet.getString("name"));
				studentDTO.setPassword(resultSet.getString("password"));
				studentDTO.setMembership_no(resultSet.getString("member_id"));
			}
		}
		return studentDTO;
	}
	
	public static boolean registerUserDataDTO(PreparedStatement preparedstatement, StudentDTO studentDTO, boolean a) throws SQLException {
		preparedstatement.setString(1, studentDTO.getEmail());
		preparedstatement.setString(2, studentDTO.getName());
		preparedstatement.setString(3, studentDTO.getPassword());
		preparedstatement.setString(4, studentDTO.getRole());
		preparedstatement.setString(5, MembershipNoGenerator.getMembershipNo(ServicesHelper.ROLE_STUDENT));
		return a = preparedstatement.execute();
	}

}
