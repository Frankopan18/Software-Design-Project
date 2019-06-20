/**
 * 
 */
package hr.finalium.ednevnik.web.profesor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.nastava.Izostanak;
import hr.finalium.ednevnik.model.nastava.Izostanak.Stanje;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Profesor;

/**
 * Servlet koji služi za opravdavanje izostanka.
 * 
 * @author Zlatko
 *
 */
@WebServlet("/servleti/opravdaj")
public class OpravdanjeIzostanaka extends HttpServlet {
	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Profesor)) {
			resp.sendError(404);
			return;
		}

		Profesor profesor = (Profesor) k;
		req.setAttribute("razred", profesor.getRazred());
		Izostanak i = DAOProvider.getDAO().dohvatiIzostanak(Long.parseLong(req.getParameter("izostanakId")));
		req.setAttribute("izostanak", i);

		req.getRequestDispatcher("/WEB-INF/profesor/urediIzostanak.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Profesor)) {
			resp.sendError(404);
			return;
		}

		Izostanak i = DAOProvider.getDAO().dohvatiIzostanak(Long.parseLong(req.getParameter("izostanakId")));
		i.setKomentarRazrednika(req.getParameter("komentarRazrednika"));
		switch (req.getParameter("stanje")) {
		case "o":
			i.setStanje(Stanje.OPRAVDAN);
			break;
		case "n":
			i.setStanje(Stanje.NEOPRAVDAN);
			break;
		default:
			i.setStanje(Stanje.NA_CEKANJU);
		}

		DAOProvider.getDAO().promijeni(i);
		resp.sendRedirect("/ednevnik/servleti/prikaziIzostanke?razredId=" + ((Profesor) k).getRazred().getId());
	}
}
