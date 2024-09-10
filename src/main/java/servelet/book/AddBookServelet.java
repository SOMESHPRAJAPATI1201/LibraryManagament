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
import utills.Validations;
import static utills.WebpageHelper.*;

@WebServlet("/addBook")
public class AddBookServelet extends HttpServlet {

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
		try {
			System.out.println("Inside Servelet Method");
			String name = req.getParameter("bookname");
			String author = req.getParameter("author");
			String edition = req.getParameter("edition");
			String quantity = req.getParameter("quantity");
			System.out.println(quantity);
			session = req.getSession();
			if (Validations.checkBooksDetails(name, author)) {
				BookDTO dto = new BookDTO();
				dto.setName(name);
				dto.setAuthor(author);
				dto.setEdition(edition);
				dto.setQuantity(Integer.parseInt(quantity));
				if (dto.getQuantity() > 0) {
					if (bookservices.addBook(dto)) {
						resp.setContentType("text/html");
						SessionHandler(session, req, resp, "Your, Book Has Been Added Sucesfully",ALERT_SUCCESS, USERINDEXPAGE);
					} else {
						SessionHandler(session, req, resp, "Book Already Exists",	ALERT_DANGER, USERINDEXPAGE);
					}
				} else {
					SessionHandler(session, req, resp, "Quantity Should Be Greater Than 0.",ALERT_DANGER, USERINDEXPAGE);
				}
			} else {
				SessionHandler(session, req, resp, "Invalid Book Details", ALERT_DANGER, USERINDEXPAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
