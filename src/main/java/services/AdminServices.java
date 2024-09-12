package services;

import dao.AdminDAO;
import dao.BookDAO;
import dao.IssueBooksDAO;
import entity.AdminDTO;
import entity.IssueBooksDTO;
import third.party.services.Gmail;
import utills.Generics;
import static utills.ServicesHelper.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AdminServices {

	private AdminDAO adminDAO;
	private IssueBooksDAO issueBookDAO;
	private BookDAO bookDAO;

	public AdminServices(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
		issueBookDAO = new IssueBooksDAO(new Generics());
		bookDAO = new BookDAO(new Generics());
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
		List<IssueBooksDTO> list = issueBookDAO.getAllEntries().stream().filter(x -> ((x.getReturn_date().isBefore(LocalDate.now())) || (x.getReturn_date().isEqual(LocalDate.now())))).collect(Collectors.toList());
		for (IssueBooksDTO dto : list) {
			if (dto.getIssued_id()==0) {
				issueBookDAO.deleteIssuedBookEntry(dto.getIssued_book_id());
				bookDAO.editBookQuantity(dto.getBook_id(), dto.getQuantity()+1);
			}
		}
		return adminDAO.getAdminLogin(email, password);
	}

	public AdminDTO getSingleAdminData(String email) {
		return adminDAO.getSingleAdminUser(email);
	}

}
