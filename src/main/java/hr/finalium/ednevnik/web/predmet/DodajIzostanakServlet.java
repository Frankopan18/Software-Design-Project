package hr.finalium.ednevnik.web.predmet;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.nastava.Izostanak;
import hr.finalium.ednevnik.model.nastava.Predmet;
import hr.finalium.ednevnik.model.nastava.Razred;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Profesor;
import hr.finalium.ednevnik.model.roles.Ucenik;
import hr.finalium.ednevnik.web.Konstante;

/**
 * 
 * @author Janko
 *
 */

@WebServlet("/servleti/predmet/dodajIzostanak")
public class DodajIzostanakServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Profesor)) {
			req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
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

		Razred razred = DAOProvider.getDAO().dohvatiRazred(idRazred);
		Predmet predmet = DAOProvider.getDAO().dohvatiPredmet(idPredmet);
		req.setAttribute("ucenici", razred.getUcenici().parallelStream().collect(Collectors.toList()));
		req.setAttribute("predmet", predmet);
		req.setAttribute("razred", razred);

		req.getRequestDispatcher("/WEB-INF/profesor/dodajIzostanak.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Profesor)) {
			req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
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
		Predmet predmet = DAOProvider.getDAO().dohvatiPredmet(idPredmet);
		if (predmet == null) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		String mailUcenik = req.getParameter("ucenik");
		if (mailUcenik == null) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		Ucenik u = (Ucenik) DAOProvider.getDAO().getKorisnik(mailUcenik);

		Izostanak izostanak = new Izostanak(req.getParameter("komentarProfesora"));
		izostanak.setPredmet(predmet);
		izostanak.setUcenik(u);

		DAOProvider.getDAO().spremi(izostanak);
		resp.sendRedirect("/ednevnik/servleti/predmet/dodajIzostanak?idPredmet=" + idPredmet + "&idRazred="
				+ u.getRazred().getId());
	}
}
