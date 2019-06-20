/**
 * 
 */
package hr.finalium.ednevnik.web.profesor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.nastava.Izostanak;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Profesor;

/**
 * Servlet koji ispisuje izostanke učenika određenog razreda.
 * 
 * @author Zlatko
 *
 */
@WebServlet("/servleti/prikaziIzostanke")
public class IspisIzostanaka extends HttpServlet {
	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Profesor)) {
			resp.sendError(404);
			return;
		}

		List<Izostanak> izostanci = DAOProvider.getDAO().dohvatiIzostanke(Long.parseLong(req.getParameter("razredId")));
		req.setAttribute("izostanci", izostanci);

		req.getRequestDispatcher("/WEB-INF/profesor/listaIzostanaka.jsp").forward(req, resp);
	}
}
