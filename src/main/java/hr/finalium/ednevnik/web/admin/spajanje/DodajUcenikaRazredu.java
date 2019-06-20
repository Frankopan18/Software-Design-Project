package hr.finalium.ednevnik.web.admin.spajanje;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.nastava.Razred;
import hr.finalium.ednevnik.model.roles.Administrator;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Ucenik;
import hr.finalium.ednevnik.web.Konstante;

/**
 * Servlet koji služi za dodavanje učenika razredu.
 * 
 * @author Zlatko
 *
 */
@WebServlet(urlPatterns = { "/servleti/admin/ucenikRazred" })
public class DodajUcenikaRazredu extends HttpServlet {

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

		req.setAttribute("razred", razred);
		req.getRequestDispatcher("/WEB-INF/admin/dodajUcenikaRazredu.jsp").forward(req, resp);
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

		Korisnik ucenik = DAOProvider.getDAO().getKorisnik(req.getParameter("mailUcenik"));

		if (!(ucenik instanceof Ucenik)) {
			req.setAttribute("greska", "Ne postoji ucenik s tom e-mail adresom");
			req.setAttribute("mailUcenik", req.getParameter("mailUcenik"));
			req.setAttribute("razred", razred);
			req.getRequestDispatcher("/WEB-INF/admin/dodajUcenikaRazredu.jsp").forward(req, resp);
			return;
		}

		DAOProvider.getDAO().spojiUcenikaIRazred(id, ucenik.getMail());
		resp.sendRedirect("/ednevnik/servleti/admin/ucenikRazred?idRazred=" + id);
	}
}
