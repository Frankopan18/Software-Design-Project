package hr.finalium.ednevnik.web.admin;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.features.Obavijest;
import hr.finalium.ednevnik.model.roles.Administrator;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.web.Konstante;

/**
 * Servlet koji dohvaća podatke koji se prikazuju adminu na naslovnoj stranici
 * te ih prosljeđuje na prikaz.
 * 
 * @author Janko
 *
 */
@WebServlet(urlPatterns = { "/servleti/admin" })
public class AdminServlet extends HttpServlet {
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

		List<Obavijest> obavijesti = DAOProvider.getDAO().dohvatiObavijesti().parallelStream()
				.filter(o -> o.isAktivna()).collect(Collectors.toList());
		req.setAttribute("obavijesti", obavijesti);

		req.getRequestDispatcher("/WEB-INF/admin.jsp").forward(req, resp);
	}
}
