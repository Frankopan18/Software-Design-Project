package hr.finalium.ednevnik.web.admin.dodavanje;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.features.Obavijest;
import hr.finalium.ednevnik.model.roles.Administrator;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.util.Util;
import hr.finalium.ednevnik.web.Konstante;

/**
 * Servlet koji omogućava administratoru da dodaje obavijesti, nakon dodavanja
 * obavijesti preusmjerava administratora na njegovu početnu stranicu.
 * 
 * @author Zlatko
 *
 */
@WebServlet(urlPatterns = { "/servleti/admin/dodajObavijest" })
@MultipartConfig
public class DodajObavijestServlet extends HttpServlet {
	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Administrator)) {
			req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		String naslov = req.getParameter("naslov");
		String tekst = req.getParameter("tekst");
		byte[] slika = Util.getBytes(req.getPart("slika").getInputStream());
		if (slika.length == 0) {
			slika = null;
		}

		Obavijest o = new Obavijest(naslov, tekst, new Date(), slika);

		DAOProvider.getDAO().spremi(o);
		resp.sendRedirect("/ednevnik/servleti/admin");
	}
}
