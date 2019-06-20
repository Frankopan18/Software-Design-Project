/**
 * 
 */
package hr.finalium.ednevnik.web.admin.spajanje;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.nastava.Predmet;
import hr.finalium.ednevnik.model.nastava.Termin;
import hr.finalium.ednevnik.model.roles.Administrator;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.web.Konstante;

/**
 * @author Zlatko
 *
 */
@WebServlet("/servleti/admin/dodajTermin")
public class DodajTerminPredmetu extends HttpServlet {
	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Administrator)) {
			req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		long id;
		try {
			id = Long.parseLong(req.getParameter("idPredmet"));
		} catch (NumberFormatException ex) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		Predmet predmet = DAOProvider.getDAO().dohvatiPredmet(id);
		if (predmet == null) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		req.setAttribute("predmet", predmet);
		req.getRequestDispatcher("/WEB-INF/admin/dodajTerminPredmetu.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Administrator)) {
			req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		long id;
		try {
			id = Long.parseLong(req.getParameter("idPredmet"));
		} catch (NumberFormatException ex) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		Predmet predmet = DAOProvider.getDAO().dohvatiPredmet(id);
		if (predmet == null) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}
		
		//TODO Termini se ne sortiraju
		Termin termin = new Termin(req.getParameter("dan"), Integer.parseInt(req.getParameter("sat")),
				req.getParameter("mjesto"));
		if (!predmet.getTermini().contains(termin)) {
			DAOProvider.getDAO().spremi(termin);
			predmet.getTermini().add(termin);
			DAOProvider.getDAO().promijeni(predmet);
		}

		resp.sendRedirect("/ednevnik/servleti/admin/dodajTermin?idPredmet=" + id);
	}
}
