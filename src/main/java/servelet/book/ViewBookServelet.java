package servelet.book;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.BookDAO;
import dto.BookDTO;
import services.BookServices;
import utills.Generics;
import static utills.SessionHelper.*;
import static utills.WebpageHelper.*;

@WebServlet("/viewBooks")
public class ViewBookServelet extends HttpServlet {

	private static final long serialVersionUID = 4397829086729463298L;
	private HttpSession session;
	private BookServices bookservices;
	private BookDAO dao;
	private Generics utills;

	@Override
	public void init() throws ServletException {
		super.init();
		utills = new Generics();
		dao = new BookDAO(utills);
		bookservices = new BookServices(dao);
	}

	@Override
	protected void service(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws ServletException {
		System.out.println("Inside View Book Servelet Method");
		ArrayList<BookDTO> list = null;
		try {
			if (bookservices.fetchAllBooks().size() > 0) {
				list = bookservices.fetchAllBooks();
				session = req.getSession();
				resp.setContentType("text/html");
				session.setAttribute("bookslist", list);
				SessionHandler(session, req, resp, "Books Fetched Succesfully", ALERT_SUCCESS, VIEWBOOKSPAGE);
			} else {
				session = req.getSession();
				resp.setContentType("text/html");
				SessionHandler(session, req, resp, "No Records Found", ALERT_WARNING, USERINDEXPAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
