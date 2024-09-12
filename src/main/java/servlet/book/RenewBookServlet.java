package servlet.book;

import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.BookDAO;
import dao.IssueBooksDAO;
import services.BookServices;
import services.IssueBookServices;
import utills.Generics;
import static utills.SessionHelper.*;
import static utills.WebpageHelper.*;

@WebServlet("/renewBook")
public class RenewBookServlet extends HttpServlet {

	private static final long serialVersionUID = 4397829086729463298L;
	private HttpSession session;
	private BookServices bookservices;
	private IssueBookServices issuebookservice;
	private BookDAO dao;
	private Generics utills;

	@Override
	public void init() throws ServletException {
		super.init();
		utills = new Generics();
		dao = new BookDAO(utills);
		bookservices = new BookServices(dao);
		issuebookservice = new IssueBookServices(new IssueBooksDAO(utills), bookservices);
	}

	@Override
	protected void doPost(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException {
		try {
			System.out.println("Inside Renew Book Servelet Method");
			String renewBookId = req.getParameter("renewBookId");
			String uniqueID = req.getParameter("unique_Id");
			System.out.println("Issued Book Id Is : " + renewBookId);
			System.out.println("Unique Id Is : " + uniqueID);
			LocalDate issuedDate = LocalDate.parse(req.getParameter("issuedDate"));
			LocalDate returnDate = LocalDate.parse(req.getParameter("returnDate"));
			System.out.println(issuedDate+"::"+returnDate);
			session = req.getSession();
				if (issuebookservice.getDateValidationByIssuerID(Integer.parseInt(renewBookId), issuedDate, returnDate )>0) {
					if (issuebookservice.renewIssuedByBookId(Integer.parseInt(renewBookId),	issuedDate, returnDate)) {
						resp.setContentType("text/html");
						SessionHandler(session, req, resp, "Your, Book Has Been Renewed Successfully", ALERT_SUCCESS, ISSUEDBOOKSERVLET+"?unique_id="+uniqueID);
					}else {
						SessionHandler(session, req, resp, "Unable to renew book.", ALERT_DANGER, ISSUEDBOOKSTUDENTVIEWPAGE);
					}
				}else {
					resp.setContentType("text/html");
					SessionHandler(session, req, resp, "You can't renew a reserved book.", ALERT_DANGER, ISSUEDBOOKSTUDENTVIEWPAGE);
				}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
