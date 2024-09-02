package servelet.book;

import java.io.IOException;
import java.util.ArrayList;

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

@WebServlet("/viewBooks")
public class ViewBookServelet extends HttpServlet {

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
	protected void service(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws ServletException {
		System.out.println("Inside View Book Servelet Method");
		try {
			if (bookservices.fetchAllBooks().size()>0) {
				ArrayList<BookDTO> list = bookservices.fetchAllBooks();
				session = req.getSession();
				resp.setContentType("text/html");
				session.setAttribute("bookslist", list);
				session.setAttribute("alert-type", "success");
				session.setAttribute("alert", "Books Fetched Succesfully");
				RequestDispatcher rd = req.getRequestDispatcher("UserIndex.jsp");
				rd.include(req, resp);
			} else {
				session = req.getSession();
				ArrayList<BookDTO> list = bookservices.fetchAllBooks();
				resp.setContentType("text/html");
				session.setAttribute("bookslist", list);
				resp.setContentType("text/html");
				session.setAttribute("alert-type", "warning");
				session.setAttribute("alert", "No Records Found");
				RequestDispatcher rd = req.getRequestDispatcher("UserIndex.jsp");
				rd.include(req, resp);
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}
}
