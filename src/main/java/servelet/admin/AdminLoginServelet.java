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

@WebServlet("/adminLogin")
public class AdminLoginServelet extends HttpServlet {

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
	protected void doPost(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException {
		try {
			System.out.println("Inside Admin Login Servlet Method");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			System.out.println(email + "::" + password);

			session = req.getSession();
			if (session != null) {
				session.invalidate();
				System.out.println("Session Invalidated");
			}
			session = req.getSession();
			AdminDTO dto = null;
			if (Validations.checkLoginCredentials(email.trim(), password.trim())) {
				System.out.println("Creds Checked : "+ email +"::"+ password);
				dto = adminservices.loginAdmin(email.trim(), password.trim());
				if (dto != null) {
					System.out.println(dto.getEmail());
					session.setAttribute("alert", dto.getName() + " , You have logged in successfully.");
					session.setAttribute("username", dto.getName());
					session.setAttribute("firstcardtype", "addBookModal");
					session.setAttribute("viewBookType", "viewAdminBookModal");
					session.setAttribute("alert-type", "success");
					session.setAttribute("userrole", "Admin");
					session.setAttribute("card1", "Add Book");
					session.setAttribute("card2", "View Books");
					RequestDispatcher rd = req.getRequestDispatcher("UserIndex.jsp");
					rd.include(req, resp);
				} else {
					System.out.println("User Not Found");
					session.setAttribute("alert", "User Not Found");
					session.setAttribute("alert-type", "warning");
					RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
					rd.include(req, resp);
				}
			} else {
				System.out.println("Invalid Creds Found");
				session.setAttribute("alert", "Invalid Credentials");
				session.setAttribute("alert-type", "warning");
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.forward(req, resp);
			}
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

}
