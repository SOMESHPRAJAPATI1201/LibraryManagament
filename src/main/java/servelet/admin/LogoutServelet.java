package servelet.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


@WebServlet("/logout")
public class LogoutServelet extends HttpServlet {

	private static final long serialVersionUID = 4397829086729463298L;
	
	@Override
	protected void service(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		System.out.println("Logout");
		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
		rd.forward(req, resp);
	}

}
