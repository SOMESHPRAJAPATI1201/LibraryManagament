package servelet.book;

import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.BookDAO;
import dao.IssueBooksDAO;
import dao.ReserveBooksDAO;
import dto.IssueBooksDTO;
import services.BookServices;
import services.IssueBookServices;
import services.ReserveBookServices;
import utills.Generics;
import static utills.SessionHelper.*;
import static utills.WebpageHelper.*;

@WebServlet("/renewBook")
public class RenewBookServelet extends HttpServlet {

	private static final long serialVersionUID = 4397829086729463298L;
	private HttpSession session;
	private BookServices bookservices;
	private IssueBookServices issuebookservice;
	private ReserveBookServices reserveservices;	
	private BookDAO dao;
	private Generics utills;

	@Override
	public void init() throws ServletException {
		super.init();
		utills = new Generics();
		dao = new BookDAO(utills);
		bookservices = new BookServices(dao);
		reserveservices = new ReserveBookServices(new ReserveBooksDAO(utills), bookservices);
		issuebookservice = new IssueBookServices(new IssueBooksDAO(utills), bookservices);
	}

	@Override
	protected void doPost(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException {
		try {
			System.out.println("Inside Renew Book Servelet Method");
			String renewBookId = req.getParameter("renewBookId");
			String bookID = req.getParameter("BookId");
			String uniqueID = req.getParameter("unique_Id");
			IssueBooksDTO issuedbookdto = issuebookservice.getIssuedBookDataByIssuedBookId(Integer.parseInt(renewBookId));
			System.out.println("Issued Book Id Is : " + renewBookId);
			System.out.println("Unique Id Is : " + uniqueID);
			System.out.println("Book Id Is : " + bookID);
			session = req.getSession();
			if (reserveservices.getAdminReserveViewBooksData(Integer.valueOf(bookID)).size()==0) {
				if (issuedbookdto.getReturn_date().isEqual(LocalDate.now())) {
					if (issuebookservice.renewIssuedByBookId(Integer.parseInt(renewBookId),	issuedbookdto.getReturn_date().plusDays(15), LocalDate.now())) {
						resp.setContentType("text/html");
						SessionHandler(session, req, resp, "Your, Book Has Been Renewed Successfully upto " + LocalDate.now().plusDays(15), ALERT_SUCCESS, ISSUEDBOOKSERVLET+"?unique_id="+uniqueID);
					} else {
						SessionHandler(session, req, resp, "Unable to renew book.", ALERT_DANGER, ISSUEDBOOKSTUDENTVIEWPAGE);
					}
				} else {
					SessionHandler(session, req, resp, "You can't renew book before " + issuedbookdto.getReturn_date(), ALERT_DANGER, ISSUEDBOOKSTUDENTVIEWPAGE);
				}
			}else {
				SessionHandler(session, req, resp, "You can't renew a reserved book.", ALERT_DANGER, ISSUEDBOOKSTUDENTVIEWPAGE);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
