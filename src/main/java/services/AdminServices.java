package services;

import dao.AdminDAO;
import dao.IssueBooksDAO;
import entity.AdminDTO;
import third.party.services.Gmail;
import utills.Generics;
import static utills.ServicesHelper.*;
import java.time.LocalDate;

public class AdminServices {

	private AdminDAO adminDAO;
	private IssueBooksDAO issueBookDAO;

	public AdminServices(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
		issueBookDAO = new IssueBooksDAO(new Generics());
	}

	public void registerAdmin(AdminDTO admindto) {
		if (adminDAO.registerAdminData(admindto) == ROWS_AFFECTED) {
			AdminDTO dto = adminDAO.getSingleAdminUser(admindto.getEmail());
			Gmail.emailSender(dto.getEmail(), dto.getPassword(), dto.getMembership_no(), ROLE_ADMIN);
			System.out.println("Account Created Sucessfully, And Creds Shared On " + dto.getEmail().toLowerCase());
		} else {
			System.err.println("Failed To Create Account");
		}
	}

	public AdminDTO loginAdmin(String email, String password) {
		issueBookDAO.getAllEntries().stream().filter(x -> (x.getReturn_date().isBefore(LocalDate.now())) || (x.getReturn_date().isEqual(LocalDate.now())))
				.map(x -> issueBookDAO.deleteIssuedBookEntry(x.getIssued_book_id()));
		return adminDAO.getAdminLogin(email, password);
	}

	public AdminDTO getSingleAdminData(String email) {
		return adminDAO.getSingleAdminUser(email);
	}

}
