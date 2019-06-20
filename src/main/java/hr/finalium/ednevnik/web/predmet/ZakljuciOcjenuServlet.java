/**
 * 
 */
package hr.finalium.ednevnik.web.predmet;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.nastava.Kategorija;
import hr.finalium.ednevnik.model.nastava.Predmet;
import hr.finalium.ednevnik.model.nastava.Razred;
import hr.finalium.ednevnik.model.nastava.Unos;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Profesor;
import hr.finalium.ednevnik.model.roles.Ucenik;
import hr.finalium.ednevnik.web.Konstante;

/**
 * @author Zlatko
 *
 */
@WebServlet("/servleti/predmet/zakljucnaOcjena")
public class ZakljuciOcjenuServlet extends HttpServlet {
	/***/
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

		req.getRequestDispatcher("/WEB-INF/profesor/zakljuciOcjenu.jsp").forward(req, resp);
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

		Integer ocjena = null;
		try {
			ocjena = Integer.parseInt(req.getParameter("ocjena"));
		} catch (NumberFormatException ex) {
			req.setAttribute("greska", "Ocjena nije odabrana");
			req.getRequestDispatcher("/WEB-INF/profesor/zakljuciOcjenu.jsp").forward(req, resp);
			return;
		}
		if (ocjena != null && (ocjena < 1 || ocjena > 5)) {
			ocjena = null;
		}

		Ucenik u = (Ucenik) DAOProvider.getDAO().getKorisnik(mailUcenik);
		if (u == null) {
			req.setAttribute("greska", "Učenik nije odabran");
			req.getRequestDispatcher("/WEB-INF/profesor/zakljuciOcjenu.jsp").forward(req, resp);
			return;
		}
		
		Kategorija kategorija = predmet.getKategorije().parallelStream()
				.filter(kat -> kat.getNaziv().equals(Konstante.ZAKLJUCNA_OCJENA)).findAny().get();

		if (kategorija.getUnosi().size() == 1) {
			Unos zakljucna = kategorija.getUnosi().stream().findFirst().get();
			kategorija.getUnosi().remove(zakljucna);
			DAOProvider.getDAO().obrisi(zakljucna.getId(), Unos.class);
		}
		Unos unos = new Unos(ocjena, req.getParameter("komentar"));
		unos.setProfesor((Profesor) k);
		unos.setUcenik(u);
		unos.setVrijemeUnosa(new Date());
		unos.setKategorija(kategorija);
		kategorija.getUnosi().add(unos);

		DAOProvider.getDAO().spremi(unos);
		DAOProvider.getDAO().promijeni(kategorija);
		resp.sendRedirect("/ednevnik/servleti/predmet/zakljucnaOcjena?idPredmet=" + idPredmet + "&idRazred="
				+ u.getRazred().getId());
	}
}
