package hr.finalium.ednevnik.web.admin.ispis;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.roles.Administrator;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Ucenik;
import hr.finalium.ednevnik.web.Konstante;

/**
 * Servlet koji omogućuje administratoru pregled svih učenika.
 * 
 * @author Zlatko
 *
 */
@WebServlet(urlPatterns = { "/servleti/admin/ispisUcenika" })
public class IspisiUcenikeServlet extends HttpServlet {
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

		List<Ucenik> ucenici = DAOProvider.getDAO().dohvatiUcenike();
		req.setAttribute("ucenici", ucenici);
		req.getRequestDispatcher("/WEB-INF/admin/ispisUcenika.jsp").forward(req, resp);
	}
}
