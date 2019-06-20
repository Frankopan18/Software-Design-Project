package hr.finalium.ednevnik.web.admin.dodavanje;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.roles.Administrator;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Spol;
import hr.finalium.ednevnik.model.roles.Ucenik;
import hr.finalium.ednevnik.model.view.UcenikViewModel;
import hr.finalium.ednevnik.util.Util;
import hr.finalium.ednevnik.web.Konstante;

/**
 * Servlet koji omogućuje administratoru da dodaje novog učenika u sustav.
 * 
 * @author Zlatko
 *
 */
@WebServlet(urlPatterns = { "/servleti/admin/dodajUcenika" })
@MultipartConfig
public class DodajUcenikaServlet extends HttpServlet {
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

		req.setAttribute("ucenik", new UcenikViewModel());
		req.getRequestDispatcher("/WEB-INF/admin/dodajUcenika.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Administrator)) {
			req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		String ime = req.getParameter("ime");
		String prezime = req.getParameter("prezime");
		String oib = req.getParameter("oib");
		String datumRodenja = req.getParameter("datumRodenja");
		String mjestoRodenja = req.getParameter("mjestoRodenja");
		String mail = req.getParameter("mail");
		String lozinka = req.getParameter("lozinka");
		String lozinkaPotvrda = req.getParameter("lozinkaPotvrda");
		Spol spol = Spol.NEPOZNATO;
		switch (req.getParameter("spol")) {
		case "m":
			spol = Spol.MUSKI;
			break;
		case "z":
			spol = Spol.ZENSKI;
		}
		byte[] slika = Util.getBytes(req.getPart("slika").getInputStream());

		UcenikViewModel model = new UcenikViewModel(mail, lozinka.hashCode(), lozinkaPotvrda.hashCode(), ime, prezime,
				oib, datumRodenja, mjestoRodenja, spol, slika);
		if (DAOProvider.getDAO().getKorisnik(mail) != null) {
			model.setMailGreska("e-mail već postoji u bazi");
		}
		if (lozinka.trim().isEmpty()) {
			model.setLozinkaGreska("lozinka nije valjana");
		}

		if (model.podaciKonzistentni()) {
			Ucenik ucenik = new Ucenik(ime, prezime, oib, datumRodenja, mjestoRodenja, spol, slika, mail,
					lozinka.hashCode());
			DAOProvider.getDAO().spremi(ucenik);
			resp.sendRedirect("/ednevnik/servleti/admin/ispisUcenika");
		} else {
			req.setAttribute("ucenik", model);
			req.getRequestDispatcher("/WEB-INF/admin/dodajUcenika.jsp").forward(req, resp);
		}
	}
}
