package services;

import dao.AdminDAO;
import dto.AdminDTO;
import third.party.services.Gmail;
import static utills.ServicesHelper.*;

public class AdminServices {
	
	private  AdminDAO adminDAO;
	
	public AdminServices(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}
	
	public void registerAdmin(AdminDTO admindto) {
		if (adminDAO.registerAdminData(admindto)==ROWS_AFFECTED) {
			AdminDTO dto = adminDAO.getSingleAdminUser(admindto.getEmail());
			Gmail.emailSender(dto.getEmail(),dto.getPassword(),dto.getMembership_no(),ROLE_ADMIN);
			System.out.println("Account Created Sucessfully, And Creds Shared On "+dto.getEmail().toLowerCase());
		}else {
			System.err.println("Failed To Create Account");
		}
	}
	
	public AdminDTO loginAdmin(String email, String password) {
		return adminDAO.getAdminLogin(email, password);
	}
	
	public AdminDTO getSingleAdminData(String email) {
		return adminDAO.getSingleAdminUser(email);
	}

}
