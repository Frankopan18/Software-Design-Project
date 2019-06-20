/**
 * 
 */
package hr.finalium.ednevnik.web.admin.dodavanje;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.nastava.Kategorija;
import hr.finalium.ednevnik.model.nastava.Predmet;
import hr.finalium.ednevnik.model.roles.Administrator;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.view.PredmetViewModel;
import hr.finalium.ednevnik.util.Util;
import hr.finalium.ednevnik.web.Konstante;

/**
 * Servlet koji služi za dodavanje predmeta.
 * 
 * @author Zlatko
 *
 */
@WebServlet(urlPatterns = { "/servleti/admin/dodajPredmet" })
@MultipartConfig
public class DodajPredmetServlet extends HttpServlet {
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

		req.setAttribute("predmet", new PredmetViewModel());
		req.getRequestDispatcher("/WEB-INF/admin/dodajPredmet.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Administrator)) {
			req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		String naziv = req.getParameter("naziv");
		String skolskaGodina = req.getParameter("skolskaGodina");
		String opis = req.getParameter("opis");
		byte[] nastavniPlan = Util.getBytes(req.getPart("nastavniPlan").getInputStream());
		byte[] nastavnoPismo = Util.getBytes(req.getPart("nastavnoPismo").getInputStream());
		String kategorije = req.getParameter("kategorije");

		PredmetViewModel model = new PredmetViewModel(0, naziv, skolskaGodina, opis, nastavniPlan, nastavnoPismo,
				kategorije);
		if (model.podaciKonzistentni()) {
			Predmet predmet = new Predmet(naziv, Integer.parseInt(skolskaGodina), opis, nastavniPlan, nastavnoPismo);
			Kategorija kategorija;
			for (String kat : kategorije.split(",")) {
				kategorija = new Kategorija(kat);
				kategorija.setPredmet(predmet);
				predmet.getKategorije().add(kategorija);
				DAOProvider.getDAO().spremi(kategorija);
			}
			kategorija = new Kategorija(Konstante.ZAKLJUCNA_OCJENA);
			kategorija.setPredmet(predmet);
			predmet.getKategorije().add(kategorija);
			DAOProvider.getDAO().spremi(kategorija);
			DAOProvider.getDAO().spremi(predmet);
			resp.sendRedirect("/ednevnik/servleti/admin/ispisPredmeta");
		} else {
			req.setAttribute("predmet", model);
			req.getRequestDispatcher("/WEB-INF/admin/dodajPredmet.jsp").forward(req, resp);
		}
	}
}
