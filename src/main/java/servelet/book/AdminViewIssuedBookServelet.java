package servelet.book;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.BookDAO;
import dao.IssueBooksDAO;
import dto.IssueBooksDTO;
import services.BookServices;
import services.IssueBookServices;
import utills.Generics;
import static utills.SessionHelper.*;
import static utills.WebpageHelper.*;

@WebServlet("/viewAdminIssuedBook")
public class AdminViewIssuedBookServelet extends HttpServlet {

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
	protected void doPost(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws ServletException {
		List<IssueBooksDTO> list = null;
		System.out.println("Inside View Issued Book Servelet Method");
		try {
			String id = req.getParameter("bookId");
			System.out.println(id);
			if (issuebookservice.getIssuedViewBooksDataByBookID(Integer.valueOf(id)).size()>0) {
				list = issuebookservice.getIssuedViewBooksDataByBookID(Integer.valueOf(id));
				session = req.getSession();
				resp.setContentType("text/html");
				session.setAttribute("issuedAdminVIewBookslist", list);
				session.setAttribute("alert-type", "success");
				session.setAttribute("alert", "Issued Books Fetched Succesfully");
				RequestDispatcher rd = req.getRequestDispatcher("AdminViewIssuedBooks.jsp");
				rd.include(req, resp);
			}
			else {
				session = req.getSession();
				resp.setContentType("text/html");
				SessionHandler(session, req, resp, "No Records Found", ALERT_WARNING, ADVIEWVIEWISSUEDBOOKPAGE, "issuedAdminVIewBookslist",list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
