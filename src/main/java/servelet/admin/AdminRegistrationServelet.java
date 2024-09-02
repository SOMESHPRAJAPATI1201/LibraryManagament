package servelet.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.AdminDAO;
import dto.AdminDTO;
import services.AdminServices;
import utills.Generics;
import utills.Validations;

@WebServlet("/adminRegistration")
public class AdminRegistrationServelet extends HttpServlet {

	private static final long serialVersionUID = 4397829086729463298L;
	HttpSession session;
	AdminServices adminservices;
	AdminDAO dao;
	Generics utills;

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
		System.out.println(email + "::" + fname+" "+lname + "::" + password+ "::" + libName+ "::" + address);
		System.out.println(adminservices.getSingleAdminData(email)==null);
		if (Validations.checkRegistrationCredentials(email, password, fname+" "+lname , address)) {
			AdminDTO admindto = new AdminDTO();
			admindto.setEmail(email);
			admindto.setName(fname+" "+lname);
			admindto.setPassword(password);
			admindto.setRole(1);
			admindto.setAddress(address);
			admindto.setLibName(libName);
			if(adminservices.getSingleAdminData(email)==null) {
				adminservices.registerAdmin(admindto);
				resp.setContentType("text/html");
				session = req.getSession();
				session.setAttribute("alert", admindto.getName() + " , You Details Have Been Registered Successfully.");
				session.setAttribute("alert-type", "success");
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.include(req, resp);
			} else {
				session = req.getSession();
				resp.setContentType("text/html");
				session.setAttribute("alert-type", "warning");
				session.setAttribute("alert", "User Already Assciated with "+email);
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.include(req, resp);
			}
		}else {
			session = req.getSession();
			resp.setContentType("text/html");
			session.setAttribute("alert", "Invalid Details");
			session.setAttribute("alert-type", "danger");
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.include(req, resp);
		}
		
	}
}
