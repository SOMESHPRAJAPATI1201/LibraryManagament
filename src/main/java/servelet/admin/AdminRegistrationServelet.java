package servelet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.AdminDAO;
import entity.AdminDTO;
import services.AdminServices;
import third.party.services.Validate_Email;
import utills.Generics;
import static utills.SessionHelper.*;
import utills.Validations;
import static utills.WebpageHelper.*;

@WebServlet("/adminRegistration")
public class AdminRegistrationServelet extends HttpServlet {

	private static final long serialVersionUID = 4397829086729463298L;
	private HttpSession session;
	private AdminServices adminservices;
	private AdminDAO dao;
	private Generics utills;

	@Override
	public void init() throws ServletException {
		super.init();
		utills = new Generics();
		dao = new AdminDAO(utills);
		adminservices = new AdminServices(dao);
	}

	@Override
	protected void doPost(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Inside Servelet Method");
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		String libName = req.getParameter("libname");
		session = req.getSession();
		if (session != null) {
			session.invalidate();
			System.out.println("Session Invalidated");
		}
		System.out.println(email + "::" + fname+" "+lname + "::" + password+ "::" + libName+ "::" + address);
		System.out.println(adminservices.getSingleAdminData(email)==null);
		if (Validations.checkRegistrationCredentials(email, password, fname+" "+lname , address)) {
			AdminDTO admindto = new AdminDTO();
			admindto.setEmail(email);
			admindto.setName(fname+" "+lname);
			admindto.setPassword(password);
			admindto.setRole("admin");
			admindto.setAddress(address);
			admindto.setLibName(libName);
			
			if (Validate_Email.isAddressValid(admindto.getEmail())) { //Email_Existence_Validation
				if(adminservices.getSingleAdminData(email)==null) {
					adminservices.registerAdmin(admindto);
					resp.setContentType("text/html");
					session = req.getSession();
					SessionHandler(session, req, resp, admindto.getName() + " , You Details Have Been Registered Successfully.", ALERT_SUCCESS, INDEXPAGE );
				} else {
					session = req.getSession();
					resp.setContentType("text/html");
					SessionHandler(session, req, resp, "User Already Assciated with "+email, ALERT_WARNING, INDEXPAGE);
				}
			}else {
				session = req.getSession();
				resp.setContentType("text/html");
				SessionHandler(session, req, resp, "Email : "+ email +" Doesn't Exists.", ALERT_WARNING, INDEXPAGE);
			}
		}else {
			session = req.getSession();
			resp.setContentType("text/html");
			SessionHandler(session, req, resp, "Invalid Details", ALERT_DANGER, INDEXPAGE);
		}
	}
}
