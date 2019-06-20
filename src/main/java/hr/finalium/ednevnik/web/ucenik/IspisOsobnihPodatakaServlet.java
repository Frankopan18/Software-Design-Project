/**
 * 
 */
package hr.finalium.ednevnik.web.ucenik;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Roditelj;
import hr.finalium.ednevnik.model.roles.Spol;
import hr.finalium.ednevnik.model.roles.Ucenik;
import hr.finalium.ednevnik.web.Konstante;

/**
 * @author Janko
 *
 */
@WebServlet("/servleti/ucenik/ispisOsobnihPodataka")
public class IspisOsobnihPodatakaServlet extends HttpServlet {

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

		Ucenik ucenik = (Ucenik) k;
		List<Roditelj> roditelji = ucenik.getRoditelji().parallelStream().collect(Collectors.toList());
		if(ucenik.getSpol() == Spol.MUSKI && ucenik.getSpol() != Spol.NEPOZNATO){
			req.setAttribute("spol", "muški");
		} else{
			req.setAttribute("spol", "ženski");
		}
		req.setAttribute("ucenik", ucenik);
		req.setAttribute("roditelji", roditelji);
		req.getRequestDispatcher("/WEB-INF/ucenik/ispisOsobnihPodataka.jsp").forward(req, resp);
	}
}
