package servelet.book;

import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.BookDAO;
import dao.IssueBooksDAO;
import dao.StudentDAO;
import dto.IssueBooksDTO;
import services.BookServices;
import services.IssueBookServices;
import services.StudentServices;
import utills.Generics;

@WebServlet("/viewAdminIssuedBook")
public class AdminViewIssuedBookServelet extends HttpServlet {

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
	protected void doPost(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws ServletException {
		ArrayList<IssueBooksDTO> list;
		System.out.println("Inside View Issued Book Servelet Method");
		try {
			String id = req.getParameter("bookId");
			System.out.println(id);
			if (issuebookservice.getAdminIssuedViewBooksData(Integer.valueOf(id)).size()>0) {
				list = issuebookservice.getAdminIssuedViewBooksData(Integer.valueOf(id));
				session = req.getSession();
				resp.setContentType("text/html");
				session.setAttribute("issuedAdminVIewBookslist", list);
				session.setAttribute("alert-type", "success");
				session.setAttribute("alert", "Issued Books Fetched Succesfully");
				RequestDispatcher rd = req.getRequestDispatcher("AdminViewIssuedBooks.jsp");
				rd.include(req, resp);
			} else {
				session = req.getSession();
				resp.setContentType("text/html");
				list = issuebookservice.getAdminIssuedViewBooksData(Integer.valueOf(id));
				session.setAttribute("issuedAdminViewBookslist", list);
				session.setAttribute("alert-type", "warning");
				session.setAttribute("alert", "No Records Found");
				RequestDispatcher rd = req.getRequestDispatcher("AdminViewIssuedBooks.jsp");
				rd.include(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
