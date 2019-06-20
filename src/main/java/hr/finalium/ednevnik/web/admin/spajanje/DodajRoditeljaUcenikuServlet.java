/**
 * 
 */
package hr.finalium.ednevnik.web.admin.spajanje;

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
import hr.finalium.ednevnik.model.roles.Ucenik;
import hr.finalium.ednevnik.web.Konstante;

/**
 * Servlet koji kod dodaje učenika roditelju i obrnuto na temelju njihovih
 * identifikatora.
 * 
 * @author Zlatko
 *
 */
@WebServlet(urlPatterns = { "/servleti/admin/ucenikRoditelj" })
public class DodajRoditeljaUcenikuServlet extends HttpServlet {
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

		req.getRequestDispatcher("/WEB-INF/admin/dodajRoditeljaUceniku.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Administrator)) {
			req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		Korisnik ucenik = DAOProvider.getDAO().getKorisnik(req.getParameter("mailUcenik"));
		Korisnik roditelj = DAOProvider.getDAO().getKorisnik(req.getParameter("mailRoditelj"));
		Ucenik u = (Ucenik) ucenik;

		boolean greska = false;
		if (!(ucenik instanceof Ucenik)) {
			req.setAttribute("mailUcenikGreska", "Ne postoji učenik s tom e-mail adresom");
			greska = true;
		}
		if (!(roditelj instanceof Roditelj)) {
			req.setAttribute("mailRoditeljGreska", "Ne postoji roditelj s tom e-mail adresom");
			greska = true;
		}
		if(u.getRoditelji().size() == 2){
			req.setAttribute("triRoditelja", "Učenik već ima 2 roditelja");
			greska = true;
		}

		if (greska) {
			req.setAttribute("mailUcenik", req.getParameter("mailUcenik"));
			req.setAttribute("mailProfesor", req.getParameter("mailProfesor"));
			req.getRequestDispatcher("/WEB-INF/admin/dodajRoditeljaUceniku.jsp").forward(req, resp);
			return;
		}

		DAOProvider.getDAO().spojiUcenikaIRoditelja(ucenik.getMail(), roditelj.getMail());
		resp.sendRedirect("/ednevnik/servleti/admin");
	}
}
