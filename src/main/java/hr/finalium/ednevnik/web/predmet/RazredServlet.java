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
import hr.finalium.ednevnik.model.nastava.Razred;
import hr.finalium.ednevnik.model.roles.Administrator;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Profesor;
import hr.finalium.ednevnik.web.Konstante;

/**
 * @author Zlatko
 *
 */
@WebServlet("/servleti/predmetRazred")
public class RazredServlet extends HttpServlet {
	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Profesor) && !(k instanceof Administrator)) {
			resp.sendError(404);
			return;
		}

		long idPredmet;
		try {
			idPredmet = Long.parseLong(req.getParameter("idPredmet"));
		} catch (NumberFormatException ex) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		long idRazred;
		try {
			idRazred = Long.parseLong(req.getParameter("idRazred"));
		} catch (NumberFormatException ex) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		Predmet predmet = DAOProvider.getDAO().dohvatiPredmet(idPredmet);
		Razred razred = DAOProvider.getDAO().dohvatiRazred(idRazred);

		req.setAttribute("predmet", predmet);
		req.setAttribute("razred", razred);

		req.getRequestDispatcher("/WEB-INF/profesor/prikazRazredaIPredmeta.jsp").forward(req, resp);
	}
}
