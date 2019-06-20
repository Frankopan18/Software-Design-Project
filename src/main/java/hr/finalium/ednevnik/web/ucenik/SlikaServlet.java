/**
 * 
 */
package hr.finalium.ednevnik.web.ucenik;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Ucenik;

/**
 * @author Janko
 *
 */
@WebServlet("/servleti/ucenik/slika")
public class SlikaServlet extends HttpServlet {

	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getParameter("mail"));
		
		if(!(k instanceof Ucenik)){
			resp.sendError(404);
			return;
		}
		
		Ucenik ucenik = (Ucenik) k;

		resp.setContentType("image/jpeg");
		resp.setHeader("Content-disposition", "attachment; filename=\"name.png\"");

		OutputStream output = resp.getOutputStream();
		output.write(ucenik.getSlika());
		output.close();
	}
}
