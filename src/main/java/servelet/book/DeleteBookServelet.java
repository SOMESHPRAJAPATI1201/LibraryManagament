package servelet.book;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.BookDAO;
import services.BookServices;
import utills.Generics;

@WebServlet("/deleteBook")
public class DeleteBookServelet extends HttpServlet {

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
	protected void doPost(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws ServletException, IOException {
        	int id = Integer.parseInt(req.getParameter("itemId"));
        	if (bookservices.deletBook(id)) {
        		resp.setContentType("text/html");
				System.out.println("Book Deleted Succesfully");
				session = req.getSession();
				session.setAttribute("alert-type", "success");
				session.setAttribute("alert", "Book Deleted Succesfully");
				RequestDispatcher rd = req.getRequestDispatcher("viewBooks");	
				rd.include(req, resp);
			}else {
				System.out.println("Failed To Delete Book");
				session = req.getSession();
				session.setAttribute("alert-type", "warning");
				session.setAttribute("alert", "Failed To Delete Book");
				RequestDispatcher rd = req.getRequestDispatcher("UserIndex.jsp");
				rd.include(req, resp);
			}	
	}
}
