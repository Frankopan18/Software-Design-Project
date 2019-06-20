package hr.finalium.ednevnik.dao.jpa;

import java.util.List;

import javax.persistence.NoResultException;

import hr.finalium.ednevnik.dao.DAO;
import hr.finalium.ednevnik.model.features.Dogadaj;
import hr.finalium.ednevnik.model.features.Obavijest;
import hr.finalium.ednevnik.model.nastava.Izostanak;
import hr.finalium.ednevnik.model.nastava.Predmet;
import hr.finalium.ednevnik.model.nastava.Razred;
import hr.finalium.ednevnik.model.nastava.Unos;
import hr.finalium.ednevnik.model.roles.Korisnik;
import hr.finalium.ednevnik.model.roles.Profesor;
import hr.finalium.ednevnik.model.roles.Roditelj;
import hr.finalium.ednevnik.model.roles.Ucenik;

/**
 * Implementacija {@link DAO} razreda koja koristi JPA za perzistenciju
 * podataka.
 * 
 * @author Zlatko
 *
 */
public class JPADAOImpl implements DAO {

	@Override
	public void spremi(Object entitet) {
		JPAEMProvider.getEntityManager().persist(entitet);
	}

	@Override
	public void promijeni(Object entitet) {
		JPAEMProvider.getEntityManager().merge(entitet);
	}

	@Override
	public <T> void obrisi(Object id, Class<T> entityClass) {
		System.out.println(JPAEMProvider.getEntityManager().find(entityClass, id));
		JPAEMProvider.getEntityManager().remove(JPAEMProvider.getEntityManager().find(entityClass, id));
	}

	@Override
	public Korisnik getKorisnik(String mail) {
		try {
			Korisnik k = (Korisnik) JPAEMProvider.getEntityManager()
					.createQuery("select k from Korisnik as k where k.mail=:email").setParameter("email", mail)
					.getSingleResult();
			return k;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public List<Ucenik> dohvatiUcenike() {
		@SuppressWarnings("unchecked")
		List<Ucenik> ucenici = (List<Ucenik>) JPAEMProvider.getEntityManager()
				.createQuery("select u from Ucenik as u order by u.prezime, u.ime").getResultList();
		return ucenici;
	}

	@Override
	public List<Obavijest> dohvatiObavijesti() {
		@SuppressWarnings("unchecked")
		List<Obavijest> obavijesti = (List<Obavijest>) JPAEMProvider.getEntityManager()
				.createQuery("select o from Obavijest as o order by o.datumStvaranja desc").getResultList();
		return obavijesti;
	}
	
	@Override
	public List<Profesor> dohvatiProfesore() {
		@SuppressWarnings("unchecked")
		List<Profesor> profesori = (List<Profesor>) JPAEMProvider.getEntityManager()
				.createQuery("select p from Profesor as p order by p.prezime, p.ime").getResultList();
		return profesori;
	}

	@Override
	public void spojiUcenikaIRoditelja(String ucenikMail, String roditeljMail) {
		Ucenik ucenik = JPAEMProvider.getEntityManager().find(Ucenik.class, ucenikMail);
		Roditelj roditelj = JPAEMProvider.getEntityManager().find(Roditelj.class, roditeljMail);
		ucenik.getRoditelji().add(roditelj);
		roditelj.getDjeca().add(ucenik);

		JPAEMProvider.getEntityManager().merge(ucenik);
		JPAEMProvider.getEntityManager().merge(roditelj);
	}

	@Override
	public List<Roditelj> dohvatiRoditelje() {
		@SuppressWarnings("unchecked")
		List<Roditelj> roditelji = (List<Roditelj>) JPAEMProvider.getEntityManager()
				.createQuery("select r from Roditelj as r order by r.prezime, r.ime").getResultList();
		return roditelji;
	}

	@Override
	public List<Predmet> dohvatiPredmete() {
		@SuppressWarnings("unchecked")
		List<Predmet> predmeti = (List<Predmet>) JPAEMProvider.getEntityManager()
				.createQuery("select p from Predmet as p order by p.skolskaGodina").getResultList();
		return predmeti;
	}

	public Predmet dohvatiPredmet(long id) {
		try {
			Predmet p = (Predmet) JPAEMProvider.getEntityManager()
					.createQuery("select p from Predmet as p where p.id=:id").setParameter("id", id).getSingleResult();
			return p;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public void spojiPredmetIProfesora(long idPredmet, String emailProfesor) {
		Predmet predmet = JPAEMProvider.getEntityManager().find(Predmet.class, idPredmet);
		Profesor profesor = JPAEMProvider.getEntityManager().find(Profesor.class, emailProfesor);

		profesor.getPredmeti().add(predmet);
		predmet.getProfesori().add(profesor);

		JPAEMProvider.getEntityManager().merge(predmet);
		JPAEMProvider.getEntityManager().merge(profesor);
	}

	@Override
	public List<Razred> dohvatiRazrede() {
		@SuppressWarnings("unchecked")
		List<Razred> razredi = (List<Razred>) JPAEMProvider.getEntityManager()
				.createQuery("select r from Razred as r order by r.oznaka").getResultList();
		return razredi;
	}

	public Razred dohvatiRazred(long id) {
		try {
			Razred r = (Razred) JPAEMProvider.getEntityManager().createQuery("select r from Razred as r where r.id=:id")
					.setParameter("id", id).getSingleResult();
			return r;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public void spojiRazredIPredmet(long idRazred, long idPredmet) {
		Predmet predmet = JPAEMProvider.getEntityManager().find(Predmet.class, idPredmet);
		Razred razred = JPAEMProvider.getEntityManager().find(Razred.class, idRazred);

		predmet.getRazredi().add(razred);
		razred.getPredmeti().add(predmet);

		JPAEMProvider.getEntityManager().merge(predmet);
		JPAEMProvider.getEntityManager().merge(razred);
	}

	@Override
	public void spojiRazredIRazrednika(long id, String mail, boolean jeRazrednik) {
		Razred razred = JPAEMProvider.getEntityManager().find(Razred.class, id);
		Profesor razrednik = JPAEMProvider.getEntityManager().find(Profesor.class, mail);

		razrednik.setRazred(razred);
		razrednik.setRazrednik(jeRazrednik);

		if (jeRazrednik) {
			razred.setRazrednik(razrednik);
		} else {
			razred.setZamjenikRazrednika(razrednik);
		}
	}

	@Override
	public void spojiUcenikaIRazred(long id, String mail) {
		Razred razred = JPAEMProvider.getEntityManager().find(Razred.class, id);
		Ucenik ucenik = JPAEMProvider.getEntityManager().find(Ucenik.class, mail);

		ucenik.setRazred(razred);
		razred.getUcenici().add(ucenik);

		JPAEMProvider.getEntityManager().merge(ucenik);
		JPAEMProvider.getEntityManager().merge(razred);
	}

	@Override
	public List<Izostanak> dohvatiIzostanke(String mail) {
		Ucenik ucenik = JPAEMProvider.getEntityManager().find(Ucenik.class, mail);
		@SuppressWarnings("unchecked")
		List<Izostanak> izostanci = (List<Izostanak>) JPAEMProvider.getEntityManager()
				.createQuery("select i from Izostanak as i where i.ucenik =:ucenik order by i.vrijeme")
				.setParameter("ucenik", ucenik).getResultList();
		return izostanci;
	}

	@Override
	public List<Izostanak> dohvatiIzostanke(long id) {
		Razred razred = JPAEMProvider.getEntityManager().find(Razred.class, id);
		@SuppressWarnings("unchecked")
		List<Izostanak> izostanci = (List<Izostanak>) JPAEMProvider.getEntityManager()
				.createQuery("select i from Izostanak as i where i.ucenik in :razred")
				.setParameter("razred", razred.getUcenici()).getResultList();
		return izostanci;
	}

	@Override
	public Izostanak dohvatiIzostanak(long id) {
		try {
			return JPAEMProvider.getEntityManager().find(Izostanak.class, id);
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public List<Unos> dohvatiUnose(Ucenik ucenik) {
		@SuppressWarnings("unchecked")
		List<Unos> unosi = (List<Unos>) JPAEMProvider.getEntityManager()
				.createQuery("select u from Unos as u where u.ucenik =:ucenik").setParameter("ucenik", ucenik)
				.getResultList();
		return unosi;
	}

	@Override
	public List<Dogadaj> dohvatiDogadaje() {
		@SuppressWarnings("unchecked")
		List<Dogadaj> dogadaji = (List<Dogadaj>) JPAEMProvider.getEntityManager()
				.createQuery("select d from Dogadaj as d order by d.vrijeme desc").getResultList();
		return dogadaji;
	}

	@Override
	public Unos dohvatiUnos(long id) {
		return (Unos) JPAEMProvider.getEntityManager().createQuery("select u from Unos as u where u.id=:id")
				.setParameter("id", id).getSingleResult();
	}

	@Override
	public Obavijest dohvatiObavijest(long id) {
		try {
			Obavijest o = (Obavijest) JPAEMProvider.getEntityManager()
					.createQuery("select o from Obavijest as o where o.id=:id").setParameter("id", id)
					.getSingleResult();
			return o;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public boolean postojiAdmin() {
		try {
			JPAEMProvider.getEntityManager().createQuery("select a from Administrator as a").getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}
}