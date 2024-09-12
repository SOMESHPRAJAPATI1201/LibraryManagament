package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.BookDAO;
import dao.IssueBooksDAO;
import dao.StudentDAO;
import entity.IssueBooksDTO;
import entity.StudentDTO;
import third.party.services.Gmail;
import utills.Generics;
import utills.ServicesHelper;

public class StudentServices {
	private StudentDAO studentDAO;
	private IssueBooksDAO issueBookDAO;
	private BookDAO bookDAO;

	public StudentServices(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
		issueBookDAO = new IssueBooksDAO(new Generics());
		bookDAO = new BookDAO(new Generics());
	}

	public ArrayList<StudentDTO> getUserData() {
		return studentDAO.getAllUserData();
	}

	public void createUser(StudentDTO studentDTO) {
		if (!studentDAO.registerUserData(studentDTO)) {
			StudentDTO dto = studentDAO.getSingleUser(studentDTO.getEmail());
			Gmail.emailSender(dto.getEmail(), dto.getPassword(), dto.getMembership_no(), ServicesHelper.ROLE_STUDENT);
			System.out.println("Account Created Sucessfully, And Creds Shared On " + dto.getEmail().toLowerCase());
		} else {
			System.err.println("Failed To Create Account");
		}
	}

	public StudentDTO loginUser(String email, String password) {
		List<IssueBooksDTO> list = issueBookDAO.getAllEntries().stream().filter(x -> ((x.getReturn_date().isBefore(LocalDate.now())) || (x.getReturn_date().isEqual(LocalDate.now())))).collect(Collectors.toList());
		for (IssueBooksDTO dto : list) {
			if (dto.getIssued_id()==0) {
				issueBookDAO.deleteIssuedBookEntry(dto.getIssued_book_id());
				bookDAO.editBookQuantity(dto.getBook_id(), dto.getQuantity()+1);
			}
		}
		return studentDAO.getLogin(email, password);
	}

	public StudentDTO getSingleUser(String email) {
		return studentDAO.getSingleUser(email);
	}

}
