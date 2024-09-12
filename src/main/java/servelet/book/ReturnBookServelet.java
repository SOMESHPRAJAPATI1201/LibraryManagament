package servelet.book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.BookDAO;
import dao.IssueBooksDAO;
import entity.BookDTO;
import entity.IssueBooksDTO;
import services.BookServices;
import services.IssueBookServices;
import utills.Generics;
import static utills.WebpageHelper.*;
import static utills.SessionHelper.*;

@WebServlet("/returnBook")
public class ReturnBookServelet extends HttpServlet {

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
			System.out.println("Inside Return Book Servelet Method");
			String issuedBookId = req.getParameter("BookId");
			String uniqueID = req.getParameter("uniqueId");
			IssueBooksDTO issuedbookdto = issuebookservice
					.getIssuedBookDataByIssuedBookId(Integer.parseInt(issuedBookId));
			BookDTO bookdto = bookservices.getBook(issuedbookdto.getBook_id());
			System.out.println("Issued Book Id And Unique Id Is : " + issuedBookId + "::" + uniqueID);
			if (issuebookservice.returnIssuedBookEntry(Integer.parseInt(issuedBookId), bookdto)) {
				resp.setContentType("text/html");
				session = req.getSession();
				SessionHandler(session, req, resp, "Your, Book Has Been Returned Sucesfully",ALERT_SUCCESS, ISSUEDBOOKSERVLET+"?unique_Id=" + uniqueID);
			} else {
				session = req.getSession();
				SessionHandler(session, req, resp, "Failed To Return Book.", ALERT_DANGER, ISSUEDBOOKSTUDENTVIEWPAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
