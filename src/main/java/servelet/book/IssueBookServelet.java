package servelet.book;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.BookDAO;
import dao.IssueBooksDAO;
import dao.StudentDAO;
import dto.BookDTO;
import dto.IssueBooksDTO;
import dto.StudentDTO;
import services.BookServices;
import services.IssueBookServices;
import services.StudentServices;
import utills.Generics;

@WebServlet("/issueBook")
public class IssueBookServelet extends HttpServlet {

	private static final long serialVersionUID = 4397829086729463298L;
	HttpSession session;
	BookServices bookservices;
	StudentServices studentervices;
	IssueBookServices issuebookservice;
	BookDAO dao;
	Generics utills;

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
			BookDTO bookdto = bookservices.getBook(Integer.valueOf(bookid.trim()));
			StudentDTO studentdto = studentervices.getSingleUser(emailid.trim());
			IssueBooksDTO issuebookdto = new IssueBooksDTO();
			issuebookdto.setBook_id(bookdto.getId());
			issuebookdto.setStudent_id(studentdto.getId());
			issuebookdto.setIssued_date(LocalDate.now());
			issuebookdto.setReturn_date(LocalDate.now().plusDays(30));
			if (bookdto.getQuantity()>0) {
				if (issuebookservice.getIssuedBooksData(studentdto.getId(),bookdto.getId()).size()==0) {
					if (issuebookservice.issueBookEntry(issuebookdto, bookdto)) {
						resp.setContentType("text/html");
						session = req.getSession();
						session.setAttribute("alert-type", "success");
						session.setAttribute("alert", "Your, Book Has Been Issued Sucesfully");
						RequestDispatcher rd = req.getRequestDispatcher("UserIndex.jsp");
						rd.include(req, resp);
					} else {
						session = req.getSession();
						session.setAttribute("alert-type", "danger");
						session.setAttribute("alert", "Failed To Issue Book");
						RequestDispatcher rd = req.getRequestDispatcher("UserIndex.jsp");
						rd.forward(req, resp);
					}
				}else {
					session = req.getSession();
					session.setAttribute("alert-type", "danger");
					session.setAttribute("alert", "Book Is Already Issued To User.");
					RequestDispatcher rd = req.getRequestDispatcher("UserIndex.jsp");
					rd.forward(req, resp);
				}
			}else {
				session = req.getSession();
				session.setAttribute("alert-type", "danger");
				session.setAttribute("alert", "Sorry ! But, book is out of stock for now.");
				RequestDispatcher rd = req.getRequestDispatcher("UserIndex.jsp");
				rd.forward(req, resp);
			}
			
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} 
	}
}
