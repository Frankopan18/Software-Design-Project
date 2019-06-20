/**
 * 
 */
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
import hr.finalium.ednevnik.model.roles.Roditelj;
import hr.finalium.ednevnik.model.view.RoditeljViewModel;
import hr.finalium.ednevnik.web.Konstante;

/**
 * @author Janko
 *
 */
@WebServlet(urlPatterns = { "/servleti/admin/urediRoditelja" })
public class UrediRoditeljaServlet extends HttpServlet {

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
		if (!(k instanceof Roditelj)) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		Roditelj roditelj = (Roditelj) k;
		req.setAttribute("roditelj", new RoditeljViewModel(roditelj));
		req.getRequestDispatcher("/WEB-INF/admin/urediRoditelja.jsp").forward(req, resp);
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
		String brojTelefona = req.getParameter("brojTelefona");

		k = DAOProvider.getDAO().getKorisnik(mail);
		if (!(k instanceof Roditelj)) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		RoditeljViewModel model;
		if (lozinka.isEmpty() && lozinkaPotvrda.isEmpty()) {
			model = new RoditeljViewModel(ime, prezime, mail, k.getLozinka(), k.getLozinka(), brojTelefona);
		} else {
			model = new RoditeljViewModel(ime, prezime, mail, lozinka.hashCode(), lozinkaPotvrda.hashCode(),
					brojTelefona);
		}

		if (model.podaciKonzistentni()) {
			Roditelj roditelj = (Roditelj) k;
			roditelj.setLozinka(model.getLozinka());
			roditelj.setIme(model.getIme());
			roditelj.setPrezime(model.getPrezime());
			roditelj.setBrojTelefona(model.getBrojTelefona());
			DAOProvider.getDAO().promijeni(roditelj);
			resp.sendRedirect("/ednevnik/servleti/admin/ispisRoditelja");
			return;
		} else {
			req.setAttribute("roditelj", model);
			req.getRequestDispatcher("/WEB-INF/admin/urediRoditelja.jsp").forward(req, resp);
		}
	}
}
