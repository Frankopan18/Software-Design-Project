package hr.finalium.ednevnik.web.ucenik;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.features.Obavijest;
import hr.finalium.ednevnik.model.nastava.Predmet;
import hr.finalium.ednevnik.model.nastava.Termin;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Ucenik;

/**
 * Servlet koji dohvaća podatke koji se prikazuju na učenikovoj početnoj
 * stranici i prosljeđuje ih za prikaz.
 * 
 * @author Janko
 *
 */
@WebServlet(urlPatterns = { "/servleti/ucenik" })
public class UcenikServlet extends HttpServlet {
	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Ucenik)) {
			resp.sendError(404);
			return;
		}

		List<Obavijest> obavijesti = DAOProvider.getDAO().dohvatiObavijesti();

		req.setAttribute("obavijesti", obavijesti);
		req.setAttribute("ucenik", k);
		if (((Ucenik) k).getRazred() != null) {
			req.setAttribute("godine", ((Ucenik) k).getRazred().getPredmeti().stream().map(p -> p.getSkolskaGodina())
					.distinct().sorted().collect(Collectors.toList()));
		}
		
		int odabranaGodina;
		try {
			odabranaGodina = Integer.parseInt(req.getParameter("godina"));
		} catch (NumberFormatException ex) {
			resp.sendError(404);
			return;
		}

		List<Predmet> predmeti = new ArrayList<>();
		if (((Ucenik) k).getRazred() != null) {
			Set<Termin> termini = new TreeSet<>();
			for (Predmet p : ((Ucenik) k).getRazred().getPredmeti()) {
				if (p.getSkolskaGodina() == odabranaGodina) {
					termini.addAll(p.getTermini());
					predmeti.add(p);
				}
			}
			req.setAttribute("termini", termini);
		}

		req.setAttribute("predmeti", predmeti);
		req.setAttribute("godina", odabranaGodina);

		req.getRequestDispatcher("/WEB-INF/ucenik.jsp").forward(req, resp);
	}
}
