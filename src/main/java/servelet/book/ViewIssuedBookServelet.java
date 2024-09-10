package servelet.book;

import java.util.ArrayList;
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

@WebServlet("/issuedBooks")
public class ViewIssuedBookServelet extends HttpServlet {

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
		System.out.println("Inside View Issued Book Servelet Method");
		try {
			String id = req.getParameter("unique_Id");
			session = req.getSession();
			if (issuebookservice.getIssuedBooksData(Integer.valueOf(id)).size() > 0) {
				ArrayList<IssueBooksDTO> list = issuebookservice.getIssuedBooksData(Integer.valueOf(id));
				resp.setContentType("text/html");
				session.setAttribute("issuedBookslist", list);
				SessionHandler(session, req, resp, "Issued Books Fetched Succesfully",ALERT_SUCCESS, ISSUEDBOOKSTUDENTVIEWPAGE);
			} else {
				resp.setContentType("text/html");
				ArrayList<IssueBooksDTO> list = issuebookservice.getIssuedBooksData(Integer.valueOf(id));
				resp.setContentType("text/html");
				session.setAttribute("issuedBookslist", list);
				SessionHandler(session, req, resp, "No Records Found", ALERT_WARNING, USERINDEXSTUDENTPAGE );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
