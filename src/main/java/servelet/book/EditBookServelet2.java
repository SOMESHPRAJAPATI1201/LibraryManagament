package servelet.book;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.BookDAO;
import dto.BookDTO;
import services.BookServices;
import utills.Generics;

@WebServlet("/editBookData")
public class EditBookServelet2 extends HttpServlet {

	private static final long serialVersionUID = 4397829086729463298L;
	HttpSession session;
	BookServices bookservices;
	BookDAO dao;
	Generics utills;

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
				dto  = new BookDTO();
				dto.setName(BookName);
				dto.setId(Integer.parseInt(BookId));
				dto.setAuthor(BookAuthor);
				dto.setEdition(BookEdition);
				dto.setQuantity(Integer.parseInt(BookQuantity));
					if (bookservices.editBook(dto)) {
						resp.setContentType("text/html");
						session = req.getSession();
						session.setAttribute("alert-type", "success");
						session.setAttribute("alert", "Your, Book Data Has Been Fetched Sucesfully.Please, Refresh Portal");
						RequestDispatcher rd = req.getRequestDispatcher("viewBooks");
						rd.include(req, resp);
					} else {
						session = req.getSession();
						session.setAttribute("alert-type", "danger");
						session.setAttribute("alert", "Failed to edit book. Please, Refresh Portal.");
						RequestDispatcher rd = req.getRequestDispatcher("ViewBooks.jsp");
						rd.forward(req, resp);
					}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
