package hr.finalium.ednevnik.web.admin.uredivanje;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.roles.Administrator;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Profesor;
import hr.finalium.ednevnik.model.view.ProfesorViewModel;
import hr.finalium.ednevnik.web.Konstante;

/**
 * Servlet koji omogućuje administratoru uređivanje podataka o profesoru.
 * 
 * @author Janko
 *
 */
@WebServlet(urlPatterns = { "/servleti/admin/urediProfesora" })
public class UrediProfesoraServlet extends HttpServlet {
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

		String mail = req.getParameter("mail");
		k = DAOProvider.getDAO().getKorisnik(mail);
		if (!(k instanceof Profesor)) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		Profesor profesor = (Profesor) k;
		req.setAttribute("profesor", new ProfesorViewModel(profesor));
		req.getRequestDispatcher("/WEB-INF/admin/urediProfesora.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Administrator)) {
			req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		String mail = req.getParameter("mail");
		String ime = req.getParameter("ime");
		String prezime = req.getParameter("prezime");
		String lozinka = req.getParameter("lozinka");
		String lozinkaPotvrda = req.getParameter("lozinkaPotvrda");
		String oib = req.getParameter("oib");
		String brojTelefona = req.getParameter("brojTelefona");

		k = DAOProvider.getDAO().getKorisnik(mail);
		if (!(k instanceof Profesor)) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		ProfesorViewModel model;
		if (lozinka.isEmpty() && lozinkaPotvrda.isEmpty()) {
			model = new ProfesorViewModel(ime, prezime, mail, k.getLozinka(), k.getLozinka(), oib, brojTelefona);
		} else {
			model = new ProfesorViewModel(ime, prezime, mail, lozinka.hashCode(), lozinkaPotvrda.hashCode(), oib,
					brojTelefona);
		}

		if (model.podaciKonzistentni()) {
			Profesor profesor = (Profesor) k;
			profesor.setLozinka(model.getLozinka());
			profesor.setIme(model.getIme());
			profesor.setPrezime(model.getPrezime());
			profesor.setOib(model.getOib());
			profesor.setBrojTelefona(model.getBrojTelefona());
			DAOProvider.getDAO().promijeni(profesor);
			resp.sendRedirect("/ednevnik/servleti/admin/ispisProfesora");
			return;
		} else {
			req.setAttribute("profesor", model);
			req.getRequestDispatcher("/WEB-INF/admin/urediProfesora.jsp").forward(req, resp);
		}
	}
}
