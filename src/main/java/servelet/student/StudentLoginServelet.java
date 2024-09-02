package servelet.student;

import java.io.IOException;
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
import utills.PageData;
import utills.Validations;

@WebServlet("/login")
public class StudentLoginServelet extends HttpServlet {

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
	protected void doPost(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException {
		try {

			System.out.println("Inside Servelet Method");
			String email = req.getParameter("email");
			String passowrd = req.getParameter("password");

			session = req.getSession();
			if (session != null) {
				session.invalidate();
				System.out.println("Session Invalidated");
			}

			session = req.getSession();

			if (Validations.checkLoginCredentials(email, passowrd)) {
				if (studentservices.loginUser(email, passowrd) != null) {
					StudentDTO dto = studentservices.loginUser(email, passowrd);
					session.setAttribute("email", dto.getEmail());
					session.setAttribute("unique_id", dto.getId());
					session.setAttribute("alert", dto.getName() + " , You have logged in succesfully.");
					session.setAttribute("username", dto.getName());
					session.setAttribute("alert-type", "success");
					session.setAttribute("userrole", "Student");
					session.setAttribute("card1", "Issued Books");
					session.setAttribute("card2", "View Books");
					session.setAttribute("firstcardtype", "issuedBookModal");
					session.setAttribute("thirdcardtype", "issuedBookModal");
					session.setAttribute("viewBookType", "viewStudentBookModal");
					//card 1st
					session.setAttribute("cardfirstfirstline", PageData.STUDENT_CARD_ONE_FIRST_LINE);
					session.setAttribute("cardfirstheading", PageData.STUDENT_CARD_ONE_HEADING);
					session.setAttribute("cardfirstthirdline", PageData.STUDENT_CARD_ONE_SECOND_LINE);
					session.setAttribute("cardfirstfourthline", PageData.STUDENT_CARD_ONE_DESCRIPTION);
					//card 2nd
					session.setAttribute("cardsecondfirstline", PageData.STUDENT_CARD_SECOND_FIRST_LINE);
					session.setAttribute("cardsecondheading", PageData.STUDENT_CARD_SECOND_HEADING);
					session.setAttribute("cardsecondthirdline", PageData.STUDENT_CARD_SECOND_SECOND_LINE);
					session.setAttribute("cardsecondfourthline", PageData.STUDENT_CARD_SECOND_DESCRIPTION);
					RequestDispatcher rd = req.getRequestDispatcher("UserIndex.jsp");
					rd.include(req, resp);
				} else {
					System.out.println("Invalid");
					resp.setContentType("text/html");
					session.setAttribute("alert", "User Not Found");
					session.setAttribute("alert-type", "warning");
					RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
					rd.include(req, resp);
				}
			} else {
				System.out.println("Invalid Credentials");
				resp.setContentType("text/html");
				session.setAttribute("alert", "Invalid Credentials");
				session.setAttribute("alert-type", "warning");
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.forward(req, resp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
