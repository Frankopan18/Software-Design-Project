/**
 * 
 */
package hr.finalium.ednevnik.web.roditelj;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.nastava.Izostanak;
import hr.finalium.ednevnik.model.nastava.Izostanak.Stanje;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Roditelj;
import hr.finalium.ednevnik.model.roles.Ucenik;
import hr.finalium.ednevnik.web.Konstante;

/**
 * @author Janko
 *
 */
@WebServlet("/servleti/roditelj/ispisIzostankaDjeteta")
public class IspisIzostankaDjetetaServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Roditelj)) {
			req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}
		
		String mail = req.getParameter("mail");
		k = DAOProvider.getDAO().getKorisnik(mail);
		if (!(k instanceof Ucenik)) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		List<Izostanak> izostanci = DAOProvider.getDAO().dohvatiIzostanke(k.getMail());
		req.setAttribute("brojIzostanka", izostanci.size());
		req.setAttribute("brojNeopravdanih", izostanci.parallelStream().filter(i -> i.getStanje() == Stanje.NEOPRAVDAN).count());
		req.setAttribute("brojOpravdanih", izostanci.parallelStream().filter(i -> i.getStanje() == Stanje.OPRAVDAN).count());
		req.setAttribute("brojNaCekanju", izostanci.parallelStream().filter(i -> i.getStanje() == Stanje.NA_CEKANJU).count());
		req.setAttribute("izostanci", izostanci);
		req.getRequestDispatcher("/WEB-INF/roditelj/ispisIzostankaDjeteta.jsp").forward(req, resp);
	}
}
