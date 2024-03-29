/**
 * 
 */
package hr.finalium.ednevnik.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet koji omogućuje logout trenutno prijavljenog korisnika.
 * 
 * @author Zlatko
 *
 */
@WebServlet(urlPatterns = { "/servleti/logout" })
public class LogoutServlet extends HttpServlet {
	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		resp.sendRedirect("/ednevnik/index.jsp");
	}
}
