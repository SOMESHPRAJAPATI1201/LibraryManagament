package servelet.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
public class LogoutServelet extends HttpServlet {

	private static final long serialVersionUID = 4397829086729463298L;
	private HttpSession session = null;

	
	@Override
	protected void service(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException, IOException {
		if (session!=null) {
			req.getSession().invalidate();
		}
		session = req.getSession();
		resp.setContentType("text/html");
		session.setAttribute("alert-type", "success");
		session.setAttribute("alert", "Logged out successfully.");
		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
		rd.forward(req, resp);
	}

}
