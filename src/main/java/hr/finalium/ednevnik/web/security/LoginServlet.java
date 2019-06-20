package hr.finalium.ednevnik.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.roles.Administrator;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Profesor;
import hr.finalium.ednevnik.model.roles.Roditelj;
import hr.finalium.ednevnik.model.roles.Ucenik;
import hr.finalium.ednevnik.util.Util;
import hr.finalium.ednevnik.web.Konstante;

/**
 * Servlet koji omogućuje login korisnika.
 * 
 * @author Zlatko
 *
 */
@WebServlet(urlPatterns = { "/index.jsp" })
public class LoginServlet extends HttpServlet {
	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mail = req.getParameter("mail");
		String lozinka = req.getParameter("lozinka");
		if (mail == null || lozinka == null) {
			resp.sendError(404);
		}

		Korisnik k = DAOProvider.getDAO().getKorisnik(mail);

		if (k != null && k.getLozinka() == lozinka.hashCode()) {
			req.getSession().setAttribute("current.user.mail", mail);
			int godina = Util.getCurrentYear();
			if (k instanceof Ucenik) {
				resp.sendRedirect("/ednevnik/servleti/ucenik?godina=" + godina);
				return;
			}
			if (k instanceof Profesor) {
				resp.sendRedirect("/ednevnik/servleti/profesor?godina=" + godina);
				return;
			}
			if (k instanceof Roditelj) {
				resp.sendRedirect("/ednevnik/servleti/roditelj");
				return;
			}
			if (k instanceof Administrator) {
				resp.sendRedirect("/ednevnik/servleti/admin");
				return;
			}
		}

		req.setAttribute("mail", mail);
		req.setAttribute("poruka", Konstante.NEUSPJELA_PRIJAVA);
		req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
	}
}
