package utills;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.IssueBooksDTO;

public class SessionHelper {
	
	
	public static final String ALERT_SUCCESS = "success";
	public static final String ALERT_DANGER = "danger";
	public static final String ALERT_WARNING = "warning";
	
	public static void SessionHandler(HttpSession session, HttpServletRequest req, HttpServletResponse resp,String alert_mesg, String alert_type, String redirect_url) {
		try {
			session.setAttribute("alert", alert_mesg);
			session.setAttribute("alert-type", alert_type);
			RequestDispatcher rd = req.getRequestDispatcher(redirect_url);
			rd.include(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void SessionHandler(HttpSession session, HttpServletRequest req, HttpServletResponse resp,String alert_mesg, String alert_type, String redirect_url,String listname, List<IssueBooksDTO> list) {
		try {
			session.setAttribute("alert", alert_mesg);
			session.setAttribute("alert-type", alert_type);
			session.setAttribute(listname, list);
			RequestDispatcher rd = req.getRequestDispatcher(redirect_url);
			rd.include(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
