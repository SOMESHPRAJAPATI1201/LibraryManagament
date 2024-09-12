package servlet.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.AdminDAO;
import entity.AdminDTO;
import services.AdminServices;
import utills.Generics;
import utills.PageData;
import static utills.WebpageHelper.*;

import static utills.SessionHelper.*;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

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
	protected void doPost(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException {
		try {
			System.out.println("Inside Admin Login Servlet Method");
			String memberid = req.getParameter("memberid");
			String password = req.getParameter("password");
			System.out.println(memberid + "::" + password);

			session = req.getSession();
			if (session != null) {
				session.invalidate();
				System.out.println("Session Invalidated");
			}
			session = req.getSession();
			AdminDTO dto = null;
				System.out.println("Creds Checked : "+ memberid +"::"+ password);
				dto = adminservices.loginAdmin(memberid.trim(), password.trim());
				if (dto != null) {
					System.out.println(dto.getEmail());
					session.setAttribute("alert", dto.getName() + " , You have logged in successfully.");
					session.setAttribute("username", dto.getName());
					session.setAttribute("firstcardtype", ADDBOOKPAGE);
					session.setAttribute("alert-type", ALERT_SUCCESS);
					session.setAttribute("userrole", "Admin");
					session.setAttribute("card1", "Add Book");
					session.setAttribute("card2", "View Books");
					//card 1st
					session.setAttribute("cardfirstfirstline", PageData.ADMIN_CARD_ONE_FIRST_LINE);
					session.setAttribute("cardfirstheading", PageData.ADMIN_CARD_ONE_HEADING);
					session.setAttribute("cardfirstthirdline", PageData.ADMIN_CARD_ONE_SECOND_LINE);
					session.setAttribute("cardfirstfourthline", PageData.ADMIN_CARD_ONE_DESCRIPTION);
					//card 2nd
					session.setAttribute("cardsecondfirstline", PageData.ADMIN_CARD_SECOND_FIRST_LINE);
					session.setAttribute("cardsecondheading", PageData.ADMIN_CARD_SECOND_HEADING);
					session.setAttribute("cardsecondthirdline", PageData.ADMIN_CARD_SECOND_SECOND_LINE);
					session.setAttribute("cardsecondfourthline", PageData.ADMIN_CARD_SECOND_DESCRIPTION);
					RequestDispatcher rd = req.getRequestDispatcher(USERINDEXPAGE);
					rd.include(req, resp);
				} else {
					System.out.println("User Not Found");
					SessionHandler(session, req, resp, "User Not Found", ALERT_WARNING, INDEXPAGE);
				}
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

}
