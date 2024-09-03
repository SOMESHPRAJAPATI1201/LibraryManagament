package servelet.book;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import dao.BookDAO;
import dao.IssueBooksDAO;
import dao.ReserveBooksDAO;
import dao.StudentDAO;
import dto.IssueBooksDTO;
import services.BookServices;
import services.IssueBookServices;
import services.ReserveBookServices;
import services.StudentServices;
import utills.Generics;

@WebServlet("/renewBook")
public class RenewBookServelet extends HttpServlet {

	private static final long serialVersionUID = 4397829086729463298L;
	HttpSession session;
	BookServices bookservices;
	StudentServices studentervices;
	IssueBookServices issuebookservice;
	ReserveBookServices reserveservices;	
	BookDAO dao;
	Generics utills;

	@Override
	public void init() throws ServletException {
		super.init();
		utills = new Generics();
		dao = new BookDAO(utills);
		bookservices = new BookServices(dao);
		studentervices = new StudentServices(new StudentDAO(utills));
		reserveservices = new ReserveBookServices(new ReserveBooksDAO(utills), bookservices);
		issuebookservice = new IssueBookServices(new IssueBooksDAO(utills), bookservices);
	}

	@Override
	protected void doPost(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException {
		try {
			System.out.println("Inside Renew Book Servelet Method");
			String renewBookId = req.getParameter("renewBookId");
			IssueBooksDTO issuedbookdto = issuebookservice.getIssuedBookDataByIssuedBookId(Integer.parseInt(renewBookId));
			System.out.println("Issued Book Id Is : " + renewBookId);
			if (reserveservices.getAdminReserveViewBooksData(Integer.valueOf(renewBookId))==null) {
				if (issuedbookdto.getReturn_date().isEqual(LocalDate.now())) {
					if (issuebookservice.renewIssuedByBookId(Integer.parseInt(renewBookId),	issuedbookdto.getReturn_date().plusDays(15), LocalDate.now())) {
						resp.setContentType("text/html");
						session = req.getSession();
						session.setAttribute("alert-type", "success");
						session.setAttribute("alert", "Your, Book Has Been Renewed Successfully upto " + LocalDate.now().plusDays(15));
						RequestDispatcher rd = req.getRequestDispatcher("UserIndex.jsp");
						rd.include(req, resp);
					} else {
						session = req.getSession();
						session.setAttribute("alert-type", "danger");
						session.setAttribute("alert", "Unable to renew book.");
						RequestDispatcher rd = req.getRequestDispatcher("UserIndex.jsp");
						rd.forward(req, resp);
					}
				} else {
					session = req.getSession();
					session.setAttribute("alert-type", "danger");
					session.setAttribute("alert", "You can't renew book before " + issuedbookdto.getReturn_date());
					RequestDispatcher rd = req.getRequestDispatcher("UserIndex.jsp");
					rd.forward(req, resp);
				}
			}else {
				session = req.getSession();
				session.setAttribute("alert-type", "danger");
				session.setAttribute("alert", "You can't renew a reserved book.");
				RequestDispatcher rd = req.getRequestDispatcher("UserIndex.jsp");
				rd.forward(req, resp);
			}	
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
