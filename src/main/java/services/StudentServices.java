package services;

import java.util.ArrayList;
import dao.StudentDAO;
import dto.StudentDTO;
import third.party.services.Gmail;

public class StudentServices {
	
	private  StudentDAO studentDAO;
	
	public StudentServices(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	public ArrayList<StudentDTO> getUserData() {
		studentDAO.getAllUserData().forEach(x->System.out.println(x.getEmail()+"::"+x.getName()+"::"+x.getId()+"::"+x.getMembership_no()));
		return studentDAO.getAllUserData();
	}
	
	public void createUser(StudentDTO studentdto) {
		if (!studentDAO.registerUserData(studentdto)) {
			StudentDTO dto = studentDAO.getSingleUser(studentdto.getEmail());
			Gmail.emailSender(dto.getEmail(),dto.getPassword(),dto.getMembership_no(),"Student");
			System.out.println("Account Created Sucessfully, And Creds Shared On "+dto.getEmail().toLowerCase());
		}else {
			System.err.println("Failed To Create Account");
		}
	}
	
	public StudentDTO loginUser(String email, String password) {
		return studentDAO.getLogin(email, password);
	}
	
	
	public StudentDTO getSingleUser(String email) {
		return studentDAO.getSingleUser(email);
	}
	
	public boolean deletUserData(String email) {
		return studentDAO.deletUserData(email);
	}

}
