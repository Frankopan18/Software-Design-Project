/**
 * 
 */
package hr.finalium.ednevnik.web.admin.spajanje;

import java.io.IOException;
import java.util.List;

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
import hr.finalium.ednevnik.web.Konstante;

/**
 * @author Zlatko
 *
 */
@WebServlet("/servleti/admin/predmetRazred")
public class DodajPredmetRazredu extends HttpServlet {
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
			id = Long.parseLong(req.getParameter("idRazred"));
		} catch (NumberFormatException ex) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		Razred razred = DAOProvider.getDAO().dohvatiRazred(id);
		if (razred == null) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		List<Predmet> predmeti = DAOProvider.getDAO().dohvatiPredmete();

		req.setAttribute("predmeti", predmeti);
		req.setAttribute("razred", razred);
		req.getRequestDispatcher("/WEB-INF/admin/dodajPredmetRazredu.jsp").forward(req, resp);
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
			id = Long.parseLong(req.getParameter("idRazred"));
		} catch (NumberFormatException ex) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		Razred razred = DAOProvider.getDAO().dohvatiRazred(id);
		if (razred == null) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		Predmet predmet = DAOProvider.getDAO().dohvatiPredmet(Integer.parseInt(req.getParameter("predmet")));

		if (predmet == null) {
			req.setAttribute("greska", "Ne postoji predmet s tim identifikatorom");
			req.setAttribute("idPredmet", req.getParameter("idPredmet"));
			req.setAttribute("razred", razred);
			req.getRequestDispatcher("/WEB-INF/admin/dodajProfesoraPredmetu.jsp").forward(req, resp);
			return;
		}

		DAOProvider.getDAO().spojiRazredIPredmet(id, predmet.getId());
		resp.sendRedirect("/ednevnik/servleti/admin/predmetRazred?idRazred=" + id);
	}
}
