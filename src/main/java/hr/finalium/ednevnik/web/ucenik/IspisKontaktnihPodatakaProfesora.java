/**
 * 
 */
package hr.finalium.ednevnik.web.ucenik;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Profesor;
import hr.finalium.ednevnik.model.roles.Ucenik;
import hr.finalium.ednevnik.web.Konstante;

/**
 * @author Janko
 *
 */
@WebServlet("/servleti/ucenik/ispisKontaktnihPodatakaProfesora")
public class IspisKontaktnihPodatakaProfesora extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Ucenik)) {
			req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		if (((Ucenik) k).getRazred() == null) {
			resp.sendError(404);
			return;
		}

		Ucenik ucenik = (Ucenik) k;
		Profesor razrednik = ucenik.getRazred().getRazrednik();
		Profesor zamjenikRazrednika = ucenik.getRazred().getZamjenikRazrednika();
		req.setAttribute("razrednik", razrednik);
		req.setAttribute("zamjenikRazrednika", zamjenikRazrednika);
		req.getRequestDispatcher("/WEB-INF/ucenik/ispisKontaktnihPodatakaProfesora.jsp").forward(req, resp);
	}
}
