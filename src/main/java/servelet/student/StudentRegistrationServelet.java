package servelet.student;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.StudentDAO;
import dto.StudentDTO;
import services.StudentServices;
import utills.Generics;
import utills.Validations;

@WebServlet("/registration")
public class StudentRegistrationServelet extends HttpServlet {

	private static final long serialVersionUID = 4397829086729463298L;
	HttpSession session;
	StudentServices studentservices;
	StudentDAO dao;
	Generics utills;

	@Override
	public void init() throws ServletException {
		super.init();
		utills = new Generics();
		dao = new StudentDAO(utills);
		studentservices = new StudentServices(dao);
	}

	@Override
	protected void doPost(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		System.out.println("Inside Servelet Method");
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		System.out.println(email + "::" + fname + " " + lname + "::" + password);
		if (Validations.checkRegistrationCredentials(email, password, fname + " " + lname)) {
			StudentDTO studentdto = new StudentDTO();
			studentdto.setEmail(email);
			studentdto.setName(fname + " " + lname);
			studentdto.setPassword(password);
			studentdto.setRole(2);
			System.out.println(studentservices.getSingleUser(email) == null);
			if (studentservices.getSingleUser(email) == null) {
				studentservices.createUser(studentdto);
				resp.setContentType("text/html");
				session = req.getSession();
				session.setAttribute("username", fname + " " + lname);
				session.setAttribute("userrole", "Student");
				session.setAttribute("alert", studentdto.getName() + " , You Details Have Been Registered Successfully.");
				session.setAttribute("alert-type", "success");
				RequestDispatcher rd = req.getRequestDispatcher("UserIndex.jsp");
				rd.include(req, resp);
			} else {
				resp.setContentType("text/html");
				System.err.println("UserAlreadyExist");
				out.println("<b style='color:red'>UserAlreadyExist</b>");
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.include(req, resp);
			}
		} else {
			resp.setContentType("text/html");
			System.err.println("InExist");
			out.println("<b style='color:red'>Invalid Credentials</b>");
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.include(req, resp);
		}

	}
}
