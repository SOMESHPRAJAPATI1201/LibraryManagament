package servelet.book;

import java.io.IOException;
import java.io.PrintWriter;

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
import services.BookServices;
import services.IssueBookServices;
import services.StudentServices;
import utills.Generics;

@WebServlet("/returnBook")
public class ReturnBookServelet extends HttpServlet {

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
			System.out.println("Inside Return Book Servelet Method");
			String issuedBookId = req.getParameter("BookId");
			String uniqueID = req .getParameter("unique_id");
			IssueBooksDTO issuedbookdto =  issuebookservice.getIssuedBookDataByIssuedBookId(Integer.parseInt(issuedBookId));
			BookDTO bookdto = bookservices.getBook(issuedbookdto.getBook_id());
			System.out.println("Issued Book Id Is : "+ issuedBookId);
			PrintWriter out = resp.getWriter();
			if (issuebookservice.returnIssuedBookEntry(Integer.parseInt(issuedBookId),bookdto)) {
					resp.setContentType("text/html");
					session = req.getSession();
					session.setAttribute("alert-type", "success");
					session.setAttribute("alert", "Your, Book Has Been Returned Sucesfully");
					out.println("Your, Book Has Been Returned Sucesfully");
					RequestDispatcher rd = req.getRequestDispatcher("issuedBooks?unique_id="+uniqueID);
					rd.include(req, resp);
			}else {
				session = req.getSession();
				session.setAttribute("alert-type", "danger");
				session.setAttribute("alert", "Failed To Return Book.");
				RequestDispatcher rd = req.getRequestDispatcher("IssuedBooksStudent.jsp");
				rd.forward(req, resp);
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} 
	}
}
