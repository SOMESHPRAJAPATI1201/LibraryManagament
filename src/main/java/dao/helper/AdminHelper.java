package dao.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dto.AdminDTO;
import utills.MembershipNoGenerator;
import utills.ServicesHelper;

public class AdminHelper {

	public static AdminDTO getAdminLoginDTO(AdminDTO adminDTO, ResultSet resultSet) throws SQLException {
		if (resultSet != null) {
			while (resultSet.next()) {
				adminDTO = new AdminDTO();
				adminDTO.setEmail(resultSet.getString("email"));
				adminDTO.setName(resultSet.getString("name"));
				adminDTO.setId(resultSet.getInt("id"));
				adminDTO.setPassword(resultSet.getString("password"));
				adminDTO.setLibName(resultSet.getString("name_of_library"));
				adminDTO.setRole(resultSet.getString("role"));
				adminDTO.setAddress(resultSet.getString("address"));
				adminDTO.setMembership_no(resultSet.getString("member_id"));
			}
		}
		return adminDTO;
	}

	public static AdminDTO getSingleAdminUserDTO(AdminDTO adminDTO, ResultSet resultSet) throws SQLException {
		if (resultSet != null) {
			while (resultSet.next()) {
				adminDTO = new AdminDTO();
				adminDTO.setEmail(resultSet.getString("email"));
				adminDTO.setName(resultSet.getString("name"));
				adminDTO.setId(resultSet.getInt("id"));
				adminDTO.setPassword(resultSet.getString("password"));
				adminDTO.setLibName(resultSet.getString("name_of_library"));
				adminDTO.setRole(resultSet.getString("role"));
				adminDTO.setAddress(resultSet.getString("address"));
				adminDTO.setMembership_no(resultSet.getString("member_id"));
			}
		}
		return adminDTO;
	}

	public static int registerAdminDataDTO(PreparedStatement preparedStatement, AdminDTO adminDTO, int a)
			throws SQLException {
		preparedStatement.setString(1, adminDTO.getEmail());
		preparedStatement.setString(2, adminDTO.getName());
		preparedStatement.setString(3, adminDTO.getPassword());
		preparedStatement.setString(4, adminDTO.getRole());
		preparedStatement.setString(5, adminDTO.getLibName());
		preparedStatement.setString(6, adminDTO.getAddress());
		preparedStatement.setString(7, MembershipNoGenerator.getMembershipNo(ServicesHelper.ROLE_ADMIN));
		return a = preparedStatement.executeUpdate();
	}

}
