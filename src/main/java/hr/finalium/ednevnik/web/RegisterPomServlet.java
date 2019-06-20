package hr.finalium.ednevnik.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.roles.Administrator;

/**
 * Pomoćni servlet koji registrira administratora. Koristiti samo kod
 * developmenta.
 * 
 * @author Zlatko
 *
 */
@WebServlet(urlPatterns = { "/register.jsp" })
public class RegisterPomServlet extends HttpServlet {
	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (DAOProvider.getDAO().postojiAdmin()) {
			req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (DAOProvider.getDAO().postojiAdmin()) {
			req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		Administrator a = new Administrator();
		a.setMail(req.getParameter("email"));
		a.setLozinka(req.getParameter("pass").hashCode());

		DAOProvider.getDAO().spremi(a);
		resp.sendRedirect("/ednevnik/index.jsp");
	}
}
