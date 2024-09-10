package servelet.book;

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

@WebServlet("/editBookData")
public class EditBookServelet2 extends HttpServlet {

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
	protected void doPost(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException {
		try {
			BookDTO dto = null;
			System.out.println("Edit Book Servelet Method");
			String BookId = req.getParameter("bookID");
			String BookName = req.getParameter("bookname");
			String BookAuthor = req.getParameter("author");
			String BookEdition = req.getParameter("edition");
			String BookQuantity = req.getParameter("quantity");
			session = req.getSession();
				dto  = new BookDTO();
				dto.setName(BookName);
				dto.setId(Integer.parseInt(BookId));
				dto.setAuthor(BookAuthor);
				dto.setEdition(BookEdition);
				dto.setQuantity(Integer.parseInt(BookQuantity));
					if (bookservices.editBook(dto)) {
						resp.setContentType("text/html");
						SessionHandler(session, req, resp, "Your, Book Data Has Been Fetched Sucesfully.Please, Refresh Portal", ALERT_SUCCESS, VIEWBOOKSERVLET);
					} else {
						SessionHandler(session, req, resp, "Failed to edit book. Please, Refresh Portal.", ALERT_DANGER, VIEWBOOKSPAGE);
					}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
