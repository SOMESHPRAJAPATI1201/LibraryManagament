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
import dao.StudentDAO;
import dto.BookDTO;
import dto.IssueBooksDTO;
import dto.ReserveBooksDTO;
import dto.StudentDTO;
import services.BookServices;
import services.IssueBookServices;
import services.ReserveBookServices;
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
	private ReserveBookServices reservebookservice;
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
		reservebookservice = new ReserveBookServices(new ReserveBooksDAO(utills), bookservices);
	}

	@Override
	protected void doPost(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException {
		try {
			System.out.println("Inside Issue Book Servelet Method");
			String bookid = (String) req.getParameter("bookId");
			String emailid = (String) req.getParameter("uniqueId");
			BookDTO bookdto = bookservices.getBook(Integer.valueOf(bookid.trim()));
			StudentDTO studentdto = studentervices.getSingleUser(emailid.trim());
			IssueBooksDTO issuebookdto = new IssueBooksDTO();
			issuebookdto.setBook_id(bookdto.getId());
			issuebookdto.setStudent_id(studentdto.getId());
			issuebookdto.setIssued_date(LocalDate.now());
			issuebookdto.setReturn_date(LocalDate.now().plusDays(30));
			ReserveBooksDTO reservedto = null;
			session = req.getSession();
			if (issuebookservice.getIssuedBooksData(studentdto.getId(), bookdto.getId()).size() == 0) {
				if (bookdto.getQuantity() > 0) {
					if (issuebookservice.issueBookEntry(issuebookdto, bookdto)) {
						resp.setContentType("text/html");
						SessionHandler(session, req, resp, "Your, Book Has Been Issued Sucesfully",	ALERT_SUCCESS, VIEWBOOKSTUDENTSERVLET);
					} else {
						resp.setContentType("text/html");
						SessionHandler(session, req, resp, "Failed To Issue Book", ALERT_DANGER, VIEWBOOKSSTUDENTSPAGE);
					}
				} else {
					if (reservebookservice.getReserveBooksData(studentdto.getId(), bookdto.getId()).size() == 0) {
						if (reservebookservice.getAdminReserveViewBooksData(bookdto.getId()).size() == 0) {
							resp.setContentType("text/html");
							LocalDate date = issuebookservice.getIssuedViewBooksDataByBookID(bookdto.getId()).get(0).getReturn_date();
							issuebookdto.setIssued_date(date.plusDays(1));
							issuebookdto.setReturn_date(date.plusDays(15));
							reservedto = new ReserveBooksDTO();
							reservedto.setBook_id(issuebookdto.getBook_id());
							reservedto.setStudent_id(issuebookdto.getStudent_id());
							reservedto.setReturn_date(date.plusDays(16));
							reservedto.setIssued_date(date.plusDays(1));
							reservebookservice.ReserveBookEntry(reservedto, bookdto);
							SessionHandler(session, req, resp,"Sorry, But Book Is Out Of Stock.But Will Be Assigned To You From "+ date.plusDays(1) + " To " + date.plusDays(15), ALERT_WARNING, VIEWBOOKSSTUDENTSPAGE);
						} else {
							SessionHandler(session, req, resp, "Book Is Already Reserved.",ALERT_DANGER, VIEWBOOKSSTUDENTSPAGE);
						}
					} else {
						SessionHandler(session, req, resp, "Book Is Already Reserved For You.", ALERT_DANGER, VIEWBOOKSSTUDENTSPAGE);
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
