package servelet.book;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.BookDAO;
import entity.BookDTO;
import services.BookServices;
import utills.Generics;
import static utills.SessionHelper.*;
import static utills.WebpageHelper.*;

@WebServlet("/editBook")
public class EditBookServelet extends HttpServlet {

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
			String issuedBookId = req.getParameter("editBookId");
			System.out.println("Book Id Is : "+ issuedBookId);
			session = req.getSession();
			if (bookservices.getBook((Integer.parseInt(issuedBookId)))!=null) {
				 dto = bookservices.getBook((Integer.parseInt(issuedBookId)));
				 System.out.println(dto.getName());
				 System.out.println(dto.getAuthor());
				 System.out.println(dto.getEdition());
				 System.out.println(dto.getQuantity());
					resp.setContentType("text/html");
					session.setAttribute("alert-type", ALERT_SUCCESS);
					session.setAttribute("bookName", dto.getName());
					session.setAttribute("bookAuthor", dto.getAuthor());
					session.setAttribute("bookEdition", dto.getEdition());
					session.setAttribute("bookQuantity", dto.getQuantity());
					session.setAttribute("bookId", dto.getId());
					session.setAttribute("alert", "Your, Book Data Has Been Fetched Sucesfully");
					RequestDispatcher rd = req.getRequestDispatcher(EDITBOOKPAGE);
					rd.include(req, resp);
			}else {
				SessionHandler(session, req, resp, "Something went wrong.", ALERT_DANGER, USERINDEXPAGE);
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} 
	}
}
