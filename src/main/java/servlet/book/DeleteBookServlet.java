package servlet.book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.BookDAO;
import services.BookServices;
import utills.Generics;
import static utills.SessionHelper.*;
import static utills.WebpageHelper.*;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {

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
	protected void doPost(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("itemId"));
		session = req.getSession();
		if (bookservices.deletBook(id)) {
			resp.setContentType("text/html");
			SessionHandler(session, req, resp, "Book Deleted Succesfully", ALERT_SUCCESS, VIEWBOOKSERVLET);
		} else {
			System.out.println("Failed To Delete Book");
			SessionHandler(session, req, resp, "Failed To Delete Book", ALERT_WARNING, VIEWBOOKSPAGE);
		}
	}
}
