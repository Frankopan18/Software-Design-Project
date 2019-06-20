/**
 * 
 */
package hr.finalium.ednevnik.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.features.Obavijest;

/**
 * @author Janko
 *
 */
@WebServlet("/servleti/obavijestSlikaServlet")
public class ObavijestSlikaServlet extends HttpServlet {

	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id;
		try {
			id = Long.parseLong(req.getParameter("id"));
		} catch (NumberFormatException ex) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}
		
		Obavijest obavijest = DAOProvider.getDAO().dohvatiObavijest(id);
		
		resp.setContentType("image/jpeg");
		resp.setHeader("Content-disposition", "attachment; filename=\"name.png\"");

		OutputStream output = resp.getOutputStream();
		output.write(obavijest.getSlika());
		output.close();
	}
}
