/**
 * 
 */
package hr.finalium.ednevnik.web.profesor;

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
import hr.finalium.ednevnik.model.nastava.Predmet;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Profesor;

/**
 * @author Zlatko
 *
 */
@WebServlet(urlPatterns = { "/servleti/profesor" })
public class ProfesorServlet extends HttpServlet {
	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Profesor)) {
			resp.sendError(404);
			return;
		}

		List<Obavijest> obavijesti = DAOProvider.getDAO().dohvatiObavijesti();
		req.setAttribute("obavijesti", obavijesti);

		int odabranaGodina;
		try {
			odabranaGodina = Integer.parseInt(req.getParameter("godina"));
		} catch (NumberFormatException ex) {
			resp.sendError(404);
			return;
		}

		List<Predmet> predmeti = ((Profesor) k).getPredmeti().parallelStream()
				.filter(p -> p.getSkolskaGodina() == odabranaGodina).collect(Collectors.toList());
		req.setAttribute("godine", ((Profesor) k).getPredmeti().parallelStream().map(p -> p.getSkolskaGodina()).distinct().sorted()
				.collect(Collectors.toList()));
		req.setAttribute("predmeti", predmeti);
		req.setAttribute("profesor", k);
		req.setAttribute("godina", odabranaGodina);

		req.getRequestDispatcher("/WEB-INF/profesor.jsp").forward(req, resp);
	}
}
