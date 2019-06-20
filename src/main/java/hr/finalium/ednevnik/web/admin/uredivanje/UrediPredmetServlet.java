/**
 * 
 */
package hr.finalium.ednevnik.web.admin.uredivanje;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.nastava.Predmet;
import hr.finalium.ednevnik.model.roles.Administrator;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.view.PredmetViewModel;
import hr.finalium.ednevnik.util.Util;
import hr.finalium.ednevnik.web.Konstante;

/**
 * Servlet koji služi za uređivanje podataka o predmetu.
 * 
 * @author Zlatko
 *
 */
@WebServlet(urlPatterns = { "/servleti/admin/urediPredmet" })
@MultipartConfig
public class UrediPredmetServlet extends HttpServlet {
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
			id = Long.parseLong(req.getParameter("id"));
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

		req.setAttribute("predmet", new PredmetViewModel(predmet));
		req.getRequestDispatcher("/WEB-INF/admin/urediPredmet.jsp").forward(req, resp);
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
			id = Long.parseLong(req.getParameter("id"));
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

		String naziv = req.getParameter("naziv");
		String skolskaGodina = req.getParameter("skolskaGodina");
		String opis = req.getParameter("opis");
		byte[] nastavniPlan = Util.getBytes(req.getPart("nastavniPlan").getInputStream());
		byte[] nastavnoPismo = Util.getBytes(req.getPart("nastavnoPismo").getInputStream());

		PredmetViewModel model = new PredmetViewModel(id, naziv, skolskaGodina, opis, nastavniPlan, nastavnoPismo,
				null);
		if (model.podaciKonzistentni()) {
			predmet.setNaziv(naziv);
			predmet.setSkolskaGodina(Integer.parseInt(skolskaGodina));
			predmet.setOpis(opis);
			if (nastavniPlan != null)
				predmet.setNastavniPlan(nastavniPlan);
			if (nastavnoPismo != null)
				predmet.setNastavnoPismo(nastavnoPismo);
			DAOProvider.getDAO().promijeni(predmet);
			resp.sendRedirect("/ednevnik/servleti/admin/ispisPredmeta");
		} else {
			req.setAttribute("predmet", model);
			req.getRequestDispatcher("/WEB-INF/admin/urediPredmet.jsp").forward(req, resp);
		}
	}
}
