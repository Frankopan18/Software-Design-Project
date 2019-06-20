/**
 * 
 */
package hr.finalium.ednevnik.web.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.features.Dogadaj;
import hr.finalium.ednevnik.model.features.Obavijest;
import hr.finalium.ednevnik.model.nastava.Kategorija;
import hr.finalium.ednevnik.model.nastava.Predmet;
import hr.finalium.ednevnik.model.nastava.Termin;
import hr.finalium.ednevnik.model.nastava.Unos;
import hr.finalium.ednevnik.model.roles.Administrator;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Profesor;
import hr.finalium.ednevnik.web.Konstante;

/**
 * Servlet koji se bavi brisanjem entiteta.
 * 
 * @author Zlatko
 *
 */
@WebServlet("/servleti/obrisi")
public class ObrisiEntitetServlet extends HttpServlet {
	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());

		switch (req.getParameter("tip")) {
		case "termin":
			if (!(k instanceof Administrator)) {
				req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
				req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
				return;
			}
			Predmet p = DAOProvider.getDAO().dohvatiPredmet(Long.parseLong(req.getParameter("zavisniId")));
			Termin t = p.getTermini().parallelStream()
					.filter(termin -> termin.getId() == Long.parseLong(req.getParameter("id"))).findFirst().get();
			p.getTermini().remove(t);
			DAOProvider.getDAO().obrisi(Long.parseLong(req.getParameter("id")), Termin.class);
			DAOProvider.getDAO()
					.spremi(new Dogadaj("Korisnik " + k.getMail() + " izbrisao je termin \"" + t + "\" predmeta " + p));
			break;
		case "unos":
			if (!(k instanceof Administrator || k instanceof Profesor)) {
				req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
				req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
				return;
			}
			Unos u = DAOProvider.getDAO().dohvatiUnos(Long.parseLong(req.getParameter("id")));
			Kategorija kat = u.getKategorija();
			kat.getUnosi().remove(u);
			u.setKategorija(null);
			DAOProvider.getDAO().obrisi(Long.parseLong(req.getParameter("id")), Unos.class);
			DAOProvider.getDAO().spremi(new Dogadaj("Korisnik " + k.getMail() + " izbrisao je unos ocjene/komentara \""
					+ u + "\" predmeta " + kat.getPredmet()));
			resp.sendRedirect("/ednevnik/servleti/ocjene?ucenik=" + req.getParameter("ucenik") + "&idPredmet="
					+ req.getParameter("idPredmet"));
			return;
		case "obavijest":
			if (!(k instanceof Administrator)) {
				req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
				req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
				return;
			}
			Obavijest o = DAOProvider.getDAO().dohvatiObavijest(Long.parseLong(req.getParameter("id")));
			DAOProvider.getDAO().obrisi(o.getId(), Obavijest.class);
			DAOProvider.getDAO().spremi(new Dogadaj(
					"Korisnik " + k.getMail() + " izbrisao je obavijest s naslovom \"" + o.getNaslov() + "\""));
			break;
		}

		resp.sendRedirect("/ednevnik/servleti/admin");
	}
}
