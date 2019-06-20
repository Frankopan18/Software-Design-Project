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
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Profesor;
import hr.finalium.ednevnik.util.Util;
import hr.finalium.ednevnik.web.Konstante;

/**
 * @author Janko
 *
 */
@WebServlet("/servleti/profesor/dodajTerminRoditeljskogSastanka")
public class DodajTerminRoditeljskogSastankaServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Profesor)) {
			req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}
		
		Profesor profesor = (Profesor) k;
		req.setAttribute("profesor", profesor);
		req.getRequestDispatcher("/WEB-INF/profesor/dodajTerminRoditeljskogSastanka.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Profesor)) {
			resp.sendError(404);
			return;
		}
	
		Profesor profesor = (Profesor) k;
		String roditeljski = req.getParameter("roditeljski");
		profesor.getRazred().setRoditeljskiSastanak(roditeljski);
		resp.sendRedirect("/ednevnik/servleti/profesor?godina=" + Util.getCurrentYear());
	}

}
