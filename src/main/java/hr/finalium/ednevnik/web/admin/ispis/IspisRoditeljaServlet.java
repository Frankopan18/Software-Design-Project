/**
 * 
 */
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
import hr.finalium.ednevnik.model.roles.Roditelj;
import hr.finalium.ednevnik.web.Konstante;

/**
 * @author Janko
 *
 */
@WebServlet(urlPatterns = { "/servleti/admin/ispisRoditelja" })
public class IspisRoditeljaServlet extends HttpServlet {
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

		List<Roditelj> roditelji = DAOProvider.getDAO().dohvatiRoditelje();
		req.setAttribute("roditelji", roditelji);
		req.getRequestDispatcher("/WEB-INF/admin/ispisRoditelja.jsp").forward(req, resp);
	}
}
