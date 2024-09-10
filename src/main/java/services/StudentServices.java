package services;

import java.util.ArrayList;
import dao.StudentDAO;
import dto.StudentDTO;
import third.party.services.Gmail;

public class StudentServices {
	private StudentDAO studentDAO;

	public StudentServices(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public ArrayList<StudentDTO> getUserData() {
		return studentDAO.getAllUserData();
	}

	public void createUser(StudentDTO studentDTO) {
		if (!studentDAO.registerUserData(studentDTO)) {
			StudentDTO dto = studentDAO.getSingleUser(studentDTO.getEmail());
			Gmail.emailSender(dto.getEmail(), dto.getPassword(), dto.getMembership_no(), "Student");
			System.out.println("Account Created Sucessfully, And Creds Shared On " + dto.getEmail().toLowerCase());
		} else {
			System.err.println("Failed To Create Account");
		}
	}

	public StudentDTO loginUser(String email, String password) {
		return studentDAO.getLogin(email, password);
	}

	public StudentDTO getSingleUser(String email) {
		return studentDAO.getSingleUser(email);
	}

}
