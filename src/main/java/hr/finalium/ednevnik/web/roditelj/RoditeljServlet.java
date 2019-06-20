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
import hr.finalium.ednevnik.model.features.Obavijest;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Roditelj;
import hr.finalium.ednevnik.web.Konstante;

/**
 * @author Janko
 *
 */
@WebServlet(urlPatterns = { "/servleti/roditelj" })
public class RoditeljServlet extends HttpServlet {

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
	
	List<Obavijest> obavijesti = DAOProvider.getDAO().dohvatiObavijesti();
	
	Roditelj r = (Roditelj) k;
	req.setAttribute("obavijesti", obavijesti);
	req.setAttribute("roditelj", r);
	req.getRequestDispatcher("/WEB-INF/roditelj.jsp").forward(req,resp);
	}
	
}
