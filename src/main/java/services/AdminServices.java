package services;

import java.util.ArrayList;
import dao.AdminDAO;
import dto.AdminDTO;
import third.party.services.Gmail;

public class AdminServices {
	
	private  AdminDAO adminDAO;
	
	public AdminServices(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}
	
	public ArrayList<AdminDTO> getAllAdminData() {
		adminDAO.getAllAdminData().forEach(x->System.out.println(x.getEmail()+"::"+x.getName()+"::"+x.getId()+"::"+x.getAddress()+"::"+x.getLibName()+"::"+x.getRole()));
		return adminDAO.getAllAdminData();
	}
	
	public void registerAdmin(AdminDTO admindto) {
		if (adminDAO.registerAdminData(admindto)==1) {
			Gmail.emailSender(admindto.getEmail(),admindto.getPassword());
			System.out.println("Account Created Sucessfully, And Creds Shared On "+admindto.getEmail().toLowerCase());
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
	
	public boolean deletAdminData(String email) {
		return adminDAO.deletAdminData(email);
	}

}
