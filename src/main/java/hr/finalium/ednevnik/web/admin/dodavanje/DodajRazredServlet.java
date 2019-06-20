/**
 * 
 */
package hr.finalium.ednevnik.web.admin.dodavanje;

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
import hr.finalium.ednevnik.model.view.RazredViewModel;
import hr.finalium.ednevnik.web.Konstante;

/**
 * @author Zlatko
 *
 */
@WebServlet(urlPatterns = { "/servleti/admin/dodajRazred" })
public class DodajRazredServlet extends HttpServlet {
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

		req.setAttribute("razred", new RazredViewModel());
		req.getRequestDispatcher("/WEB-INF/admin/dodajRazred.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Administrator)) {
			req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		String oznaka = req.getParameter("oznaka");
		String godina = req.getParameter("godina");

		RazredViewModel model = new RazredViewModel(0, oznaka, godina);
		if (model.podaciKonzistentni()) {
			Razred razred = new Razred(oznaka, Integer.parseInt(godina));
			DAOProvider.getDAO().spremi(razred);
			resp.sendRedirect("/ednevnik/servleti/admin/dodajRazred");
		} else {
			req.setAttribute("razred", model);
			req.getRequestDispatcher("/WEB-INF/admin/dodajRazred.jsp").forward(req, resp);
		}
	}
}
