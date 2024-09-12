package servlet.student;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.StudentDAO;
import entity.StudentDTO;
import services.StudentServices;
import utills.Generics;
import utills.PageData;
import static utills.SessionHelper.*;
import static utills.WebpageHelper.*;

@WebServlet("/login")
public class StudentLoginServlet extends HttpServlet {

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
	protected void doPost(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException {
		try {

			System.out.println("Inside Servelet Method");
			String memberid = req.getParameter("memberid");
			String passowrd = req.getParameter("password");

			session = req.getSession();
			if (session != null) {
				session.invalidate();
				System.out.println("Session Invalidated");
			}

			session = req.getSession();
			System.out.println(memberid+"::"+passowrd);
				if (studentservices.loginUser(memberid, passowrd) != null) {
					StudentDTO dto = studentservices.loginUser(memberid, passowrd);
					session.setAttribute("email", dto.getEmail());
					session.setAttribute("unique_id", dto.getId());
					session.setAttribute("alert", dto.getName() + " , You have logged in succesfully.");
					session.setAttribute("username", dto.getName());
					session.setAttribute("alert-type", ALERT_SUCCESS);
					session.setAttribute("userrole", "Student");
					session.setAttribute("card1", "Issued Books");
					session.setAttribute("card2", "View Books");
					session.setAttribute("firstcardtype", ISSUEDBOOKSERVLET);
					session.setAttribute("viewBookType", VIEWBOOKSTUDENTSERVLET);
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
					RequestDispatcher rd = req.getRequestDispatcher(USERINDEXSTUDENTPAGE);
					rd.include(req, resp);
				} else {
					System.out.println("Invalid");
					resp.setContentType("text/html");
					SessionHandler(session, req, resp, "User Not Found", ALERT_WARNING, INDEXPAGE);
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
