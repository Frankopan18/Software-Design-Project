package hr.finalium.ednevnik.web.admin.uredivanje;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.finalium.ednevnik.dao.DAOProvider;
import hr.finalium.ednevnik.model.roles.Administrator;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Spol;
import hr.finalium.ednevnik.model.roles.Ucenik;
import hr.finalium.ednevnik.model.view.UcenikViewModel;
import hr.finalium.ednevnik.util.Util;
import hr.finalium.ednevnik.web.Konstante;

/**
 * Servlet koji pri get zahtjevu dohvaća podatke o učeniku i prosljeđuje ih na
 * ispis, a pri post zahtjevu prima podatke o učeniku i sprema ih.
 * 
 * @author Zlatko
 *
 */
@WebServlet(urlPatterns = { "/servleti/admin/urediUcenika" })
@MultipartConfig
public class UrediUcenikaServlet extends HttpServlet {
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

		String mail = req.getParameter("mail");
		k = DAOProvider.getDAO().getKorisnik(mail);
		if (!(k instanceof Ucenik)) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		Ucenik ucenik = (Ucenik) k;
		req.setAttribute("ucenik", new UcenikViewModel(ucenik));
		req.getRequestDispatcher("/WEB-INF/admin/urediUcenika.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Korisnik k = DAOProvider.getDAO().getKorisnik(req.getSession().getAttribute("current.user.mail").toString());
		if (!(k instanceof Administrator)) {
			req.setAttribute("poruka", Konstante.NEMA_OVLASTI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		String mail = req.getParameter("mail");
		String ime = req.getParameter("ime");
		String prezime = req.getParameter("prezime");
		String lozinka = req.getParameter("lozinka");
		String lozinkaPotvrda = req.getParameter("lozinkaPotvrda");
		String oib = req.getParameter("oib");
		String datumRodenja = req.getParameter("datumRodenja");
		String mjestoRodenja = req.getParameter("mjestoRodenja");
		Spol spol = Spol.NEPOZNATO;
		switch (req.getParameter("spol")) {
		case "m":
			spol = Spol.MUSKI;
			break;
		case "z":
			spol = Spol.ZENSKI;
		}
		byte[] slika = Util.getBytes(req.getPart("slika").getInputStream());

		k = DAOProvider.getDAO().getKorisnik(mail);
		if (!(k instanceof Ucenik)) {
			req.setAttribute("poruka", Konstante.NE_POSTOJI);
			req.getRequestDispatcher("/WEB-INF/ErrorPage.jsp").forward(req, resp);
			return;
		}

		UcenikViewModel model;
		if (lozinka.isEmpty() && lozinkaPotvrda.isEmpty()) {
			model = new UcenikViewModel(mail, k.getLozinka(), k.getLozinka(), ime, prezime, oib, datumRodenja,
					mjestoRodenja, spol, slika);
		} else {
			model = new UcenikViewModel(mail, lozinka.hashCode(), lozinkaPotvrda.hashCode(), ime, prezime, oib,
					datumRodenja, mjestoRodenja, spol, slika);
		}

		if (model.podaciKonzistentni()) {
			Ucenik ucenik = (Ucenik) k;
			ucenik.setLozinka(model.getLozinka());
			ucenik.setIme(model.getIme());
			ucenik.setPrezime(model.getPrezime());
			ucenik.setOib(model.getOib());
			ucenik.setDatumRodenja(model.getDatumRodenja());
			ucenik.setMjestoRodenja(model.getMjestoRodenja());
			ucenik.setSpol(model.getSpol());
			if (model.getSlika().length != 0) {
				ucenik.setSlika(model.getSlika());
			}
			DAOProvider.getDAO().promijeni(ucenik);
			resp.sendRedirect("/ednevnik/servleti/admin/ispisUcenika");
			return;
		} else {
			req.setAttribute("ucenik", model);
			req.getRequestDispatcher("/WEB-INF/admin/urediUcenika.jsp").forward(req, resp);
		}
	}
}
