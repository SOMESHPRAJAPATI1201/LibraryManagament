package servelet.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.StudentDAO;
import entity.StudentDTO;
import services.StudentServices;
import third.party.services.Validate_Email;
import utills.Generics;
import static utills.SessionHelper.*;
import utills.Validations;
import static utills.WebpageHelper.*;

@WebServlet("/studentRegistration")
public class StudentRegistrationServelet extends HttpServlet {

	private static final long serialVersionUID = 4397829086729463298L;
	private HttpSession session;
	private StudentServices studentservices;
	private StudentDAO dao;
	private Generics utills;

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
		System.out.println("Inside Student Register Servelet Method");
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		session = req.getSession();
		if (session != null) {
			session.invalidate();
			System.out.println("Session Invalidated");
		}
		System.out.println(email + "::" + fname + " " + lname + "::" + password);
		if (Validations.checkRegistrationCredentials(email, password, fname + " " + lname)) {
			StudentDTO studentdto = new StudentDTO();
			studentdto.setEmail(email);
			studentdto.setName(fname + " " + lname);
			studentdto.setPassword(password);
			studentdto.setRole("student");
			session = req.getSession();
			if (Validate_Email.isAddressValid(email)) {      //Email_Existence_Validation
				if (studentservices.getSingleUser(email)==null) {
					studentservices.createUser(studentdto);
					resp.setContentType("text/html");
					SessionHandler(session, req, resp, studentdto.getName() + " , You Details Have Been Registered Successfully.", ALERT_SUCCESS, INDEXPAGE);
				} else {
					resp.setContentType("text/html");
					SessionHandler(session, req, resp, "User Already Assciated with "+email, ALERT_WARNING, INDEXPAGE);
				}
			}else {
				resp.setContentType("text/html");
				SessionHandler(session, req, resp, "Email : "+ email+" Doesn't Exists.", ALERT_WARNING, INDEXPAGE);
			}	
		} else {
			resp.setContentType("text/html");
			SessionHandler(session, req, resp,"Invalid Credentials.", ALERT_DANGER, INDEXPAGE);
		}

	}
}
