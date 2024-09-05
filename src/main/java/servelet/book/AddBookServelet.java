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
import utills.Validations;

@WebServlet("/addBook")
public class AddBookServelet extends HttpServlet {

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
			System.out.println("Inside Servelet Method");
			String name = req.getParameter("bookname");
			String author = req.getParameter("author");
			String edition = req.getParameter("edition");
			String quantity = req.getParameter("quantity");
			System.out.println(name + "::" + author + "::" + edition + "::" + quantity);
			if (Validations.checkBooksDetails(name, author)) {
				BookDTO dto = new BookDTO();
				dto.setName(name);
				dto.setAuthor(author);
				dto.setEdition(edition);
				dto.setQuantity(Integer.parseInt(quantity));
				if (bookservices.addBook(dto)) {
					resp.setContentType("text/html");
					session = req.getSession();
					session.setAttribute("alert-type", "success");
					session.setAttribute("alert", "Your, Book Has Been Added Sucesfully");
					RequestDispatcher rd = req.getRequestDispatcher("UserIndex.jsp");
					rd.include(req, resp);
				} else {
					session = req.getSession();
					session.setAttribute("alert-type", "danger");
					session.setAttribute("alert", "Book Already Exists");
					RequestDispatcher rd = req.getRequestDispatcher("UserIndex.jsp");
					rd.forward(req, resp);
				}
			} else {
				session = req.getSession();
				session.setAttribute("alert-type", "danger");
				session.setAttribute("alert", "Invalid Book Details");
				RequestDispatcher rd = req.getRequestDispatcher("UserIndex.jsp");
				rd.forward(req, resp);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
