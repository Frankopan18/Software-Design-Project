/**
 * 
 */
package hr.finalium.ednevnik.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.nastava.Predmet;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Profesor;
import hr.finalium.ednevnik.model.roles.Ucenik;

/**
 * @author Zlatko
 *
 */
@WebServlet("/servleti/ocjene")
public class OcjeneServlet extends HttpServlet {
	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());

		if (k instanceof Ucenik) {
			if (!k.getMail().equals(req.getParameter("ucenik"))) {
				req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
				req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
				return;
			}
			req.setAttribute("ucenik", k);
		}
		if (k instanceof Profesor) {
			req.setAttribute("profesor", k);
		}

		long idPredmet;
		try {
			idPredmet = Long.parseLong(req.getParameter("idPredmet"));
		} catch (NumberFormatException ex) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		Predmet predmet = DAOProvider.getDAO().dohvatiPredmet(idPredmet);

		Ucenik ucenik = (Ucenik) DAOProvider.getDAO().getKorisnik(req.getParameter("ucenik"));
		if (ucenik == null) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		req.setAttribute("predmet", predmet);
		req.setAttribute("unosi", DAOProvider.getDAO().dohvatiUnose(ucenik));
		req.setAttribute("ucenik", ucenik);
		req.setAttribute("kategorije", predmet.getKategorije());

		req.getRequestDispatcher("/WEB-INF/prikazOcjena.jsp").forward(req, resp);
	}
}
