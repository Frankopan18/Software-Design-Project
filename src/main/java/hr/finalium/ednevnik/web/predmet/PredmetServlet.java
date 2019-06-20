/**
 * 
 */
package hr.finalium.ednevnik.web.predmet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.nastava.Predmet;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Ucenik;
import hr.finalium.ednevnik.web.Konstante;

/**
 * @author Zlatko
 *
 */
@WebServlet(urlPatterns = { "/servleti/predmet" })
public class PredmetServlet extends HttpServlet {
	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (k instanceof Ucenik) {
			resp.sendRedirect("/ednevnik/servleti/ucenik");
		}

		long id;
		try {
			id = Long.parseLong(req.getParameter("id"));
		} catch (NumberFormatException ex) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		Predmet predmet = DAOProvider.getDAO().dohvatiPredmet(id);
		req.setAttribute("predmet", predmet);
		req.getRequestDispatcher("/WEB-INF/profesor/prikazPredmeta.jsp").forward(req, resp);
	}
}
