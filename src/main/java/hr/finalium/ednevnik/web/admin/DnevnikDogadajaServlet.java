/**
 * 
 */
package hr.finalium.ednevnik.web.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.roles.Administrator;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.web.Konstante;

/**
 * Servlet koji prosljeđuje dnevnik događaja na ispis.
 * 
 * @author Zlatko
 *
 */
@WebServlet("/servleti/admin/dnevnikDogadaja")
public class DnevnikDogadajaServlet extends HttpServlet {
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

		req.setAttribute("dogadaji", DAOProvider.getDAO().dohvatiDogadaje());
		req.getRequestDispatcher("/WEB-INF/admin/dnevnikDogadaja.jsp").forward(req, resp);
	}
}
