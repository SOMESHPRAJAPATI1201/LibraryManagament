package servelet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static utills.SessionHelper.*;
import static utills.WebpageHelper.*;


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
		SessionHandler(session, req, resp, "Logged out successfully.", ALERT_SUCCESS, INDEXPAGE);
	}

}
