package servelet.book;

import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.BookDAO;
import dao.IssueBooksDAO;
import dao.StudentDAO;
import entity.BookDTO;
import entity.IssueBooksDTO;
import entity.StudentDTO;
import services.BookServices;
import services.IssueBookServices;
import services.StudentServices;
import utills.Generics;
import static utills.SessionHelper.*;
import static utills.WebpageHelper.*;

@WebServlet("/issueBook")
public class IssueBookServelet extends HttpServlet {

	private static final long serialVersionUID = 4397829086729463298L;
	private HttpSession session;
	private BookServices bookservices;
	private StudentServices studentervices;
	private IssueBookServices issuebookservice;
	private BookDAO dao;
	private Generics utills;

	@Override
	public void init() throws ServletException {
		super.init();
		utills = new Generics();
		dao = new BookDAO(utills);
		bookservices = new BookServices(dao);
		studentervices = new StudentServices(new StudentDAO(utills));
		issuebookservice = new IssueBookServices(new IssueBooksDAO(utills), bookservices);
	}

	@Override
	protected void doPost(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException {
		try {
			System.out.println("Inside Issue Book Servelet Method");
			String bookid = (String) req.getParameter("bookId");
			String emailid = (String) req.getParameter("uniqueId");
			LocalDate issuedDate = LocalDate.parse(req.getParameter("issuedDate"));
			LocalDate returnedDate = LocalDate.parse(req.getParameter("returnDate"));
			System.out.println(issuedDate+"::"+returnedDate);
			BookDTO bookdto = bookservices.getBook(Integer.valueOf(bookid.trim())); 
			StudentDTO studentdto = studentervices.getSingleUser(emailid.trim());
			IssueBooksDTO issuebookdto = new IssueBooksDTO();
			issuebookdto.setBook_id(bookdto.getId());
			issuebookdto.setStudent_id(studentdto.getId());
			issuebookdto.setIssued_date(issuedDate);
			issuebookdto.setReturn_date(returnedDate);
			session = req.getSession();
			if (issuebookservice.getIssuedBooksData(studentdto.getId(), bookdto.getId()).size() == 0) {
				int issued_book_id = issuebookservice.getDateValidation(issuedDate, returnedDate, bookdto.getId());
				System.out.println(issued_book_id);
				if (issued_book_id>0) {
					issuebookdto.setIssued_id(issued_book_id);
					if (issuebookservice.issueSameBookEntry(issuebookdto, bookdto)) {
						resp.setContentType("text/html");
						SessionHandler(session, req, resp, "Your, Book Has Been Issued Sucesfully With Re-Issued Book ID : "+issued_book_id,	ALERT_SUCCESS, VIEWBOOKSSTUDENTSPAGE);
					} else {
						resp.setContentType("text/html");
						SessionHandler(session, req, resp, "Failed To Issue Book", ALERT_DANGER, VIEWBOOKSSTUDENTSPAGE);
					}
				} else {
					if (bookdto.getQuantity() > 0) {
						if (issuebookservice.issueBookEntry(issuebookdto, bookdto)) {
							resp.setContentType("text/html");
							SessionHandler(session, req, resp, "Your, Book Has Been Issued Sucesfully",	ALERT_SUCCESS, VIEWBOOKSTUDENTSERVLET);
						} else {
							resp.setContentType("text/html");
							SessionHandler(session, req, resp, "Failed To Issue Book", ALERT_DANGER, VIEWBOOKSSTUDENTSPAGE);
						}
					} else {
						SessionHandler(session, req, resp, "Book Is Out Of Stock", ALERT_DANGER, VIEWBOOKSSTUDENTSPAGE);
					}
				}
			} else {
				SessionHandler(session, req, resp, "Book Is Already Issued To User.", ALERT_DANGER, VIEWBOOKSSTUDENTSPAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
