package hr.finalium.ednevnik.web.admin.dodavanje;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.roles.Administrator;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Roditelj;
import hr.finalium.ednevnik.model.view.RoditeljViewModel;
import hr.finalium.ednevnik.web.Konstante;

/**
 * Servlet koji omogućuje administratoru da dodaje novog roditelja u sustav.
 * 
 * @author Zlatko
 *
 */
@WebServlet(urlPatterns = { "/servleti/admin/dodajRoditelja" })
public class DodajRoditeljaServlet extends HttpServlet {
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

		req.setAttribute("roditelj", new RoditeljViewModel());
		req.getRequestDispatcher("/WEB-INF/admin/dodajRoditelja.jsp").forward(req, resp);
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
		String mail = req.getParameter("mail");
		String lozinka = req.getParameter("lozinka");
		String lozinkaPotvrda = req.getParameter("lozinkaPotvrda");
		String brojTelefona = req.getParameter("brojTelefona");

		RoditeljViewModel model = new RoditeljViewModel(ime, prezime, mail, lozinka.hashCode(),
				lozinkaPotvrda.hashCode(), brojTelefona);
		if (DAOProvider.getDAO().getKorisnik(mail) != null) {
			model.setMailGreska("e-mail već postoji u bazi");
		}
		if (lozinka.trim().isEmpty()) {
			model.setLozinkaGreska("lozinka nije valjana");
		}

		if (model.podaciKonzistentni()) {
			Roditelj roditelj = new Roditelj(ime, prezime, brojTelefona, mail, lozinka.hashCode());
			DAOProvider.getDAO().spremi(roditelj);
			resp.sendRedirect("/ednevnik/servleti/admin");
		} else {
			req.setAttribute("roditelj", model);
			req.getRequestDispatcher("/WEB-INF/admin/dodajRoditelja.jsp").forward(req, resp);
		}
	}
}
