package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.IssueBooksDAO;
import dao.StudentDAO;
import entity.IssueBooksDTO;
import entity.StudentDTO;
import third.party.services.Gmail;
import utills.Generics;

public class StudentServices {
	private StudentDAO studentDAO;
	private IssueBooksDAO issueBookDAO;

	public StudentServices(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
		issueBookDAO = new IssueBooksDAO(new Generics());
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
		List<IssueBooksDTO> list = issueBookDAO.getAllEntries().stream().filter(x -> (x.getReturn_date().isBefore(LocalDate.now())) || (x.getReturn_date().isEqual(LocalDate.now()))).collect(Collectors.toList());
		for (IssueBooksDTO dto : list) {
			issueBookDAO.deleteIssuedBookEntry(dto.getIssued_book_id());
			System.out.println(dto.getIssued_book_id()+" Deleted");
		}
		return studentDAO.getLogin(email, password);
	}

	public StudentDTO getSingleUser(String email) {
		return studentDAO.getSingleUser(email);
	}

}
