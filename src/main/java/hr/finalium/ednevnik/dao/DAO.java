package hr.finalium.ednevnik.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

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
 * Data Access Object. Sučelje koje definira metode dohvata i spremanja
 * podataka.
 * 
 * @author Zlatko
 *
 */
public interface DAO {

	/**
	 * Sprema entitet.
	 * 
	 * @param entitet
	 *            entitet koji se sprema
	 * @throws EntityNotFoundException
	 *             ako entitet nije valjan
	 */
	void spremi(Object entitet);

	/**
	 * Sprema promijenjeni entitet.
	 * 
	 * @param entitet
	 *            entitet kojem su promijenjena svojstva
	 * @throws EntityNotFoundException
	 *             ako entitet nije valjan
	 */
	void promijeni(Object entitet);

	/**
	 * Briše entitet.
	 * 
	 * @param id
	 *            ID entiteta
	 * @param entityClass
	 *            razred entiteta
	 */
	<T> void obrisi(Object id, Class<T> entityClass);

	/**
	 * Dohvaća korisnika s određenom e-mail adresom.
	 * 
	 * @param mail
	 *            e-mail korisnika
	 * @return korisnik, null ako takav ne postoji
	 */
	Korisnik getKorisnik(String mail);

	/**
	 * Dohvaća obavijesti.
	 * 
	 * @return lista obavijesti
	 */
	List<Obavijest> dohvatiObavijesti();

	/**
	 * Dohvaća obavijest.
	 * 
	 * @param id
	 *            ID obavijest
	 * @return obavijest
	 */
	Obavijest dohvatiObavijest(long id);

	/**
	 * Dohvaća sve učenike iz baze podataka.
	 * 
	 * @return učenici
	 */
	List<Ucenik> dohvatiUcenike();

	/**
	 * Dohvaća sve profesore iz baze podataka.
	 * 
	 * @return profesori
	 */
	List<Profesor> dohvatiProfesore();

	/**
	 * Spaja učenika i roditelja s doređenim e-mail adresama.
	 * 
	 * @param ucenikMail
	 *            e-mail adresa učenika
	 * @param roditeljMail
	 *            e-mail adresa roditelja
	 */
	void spojiUcenikaIRoditelja(String ucenikMail, String roditeljMail);

	/**
	 * Dohvaća sve roditelje iz baze podataka.
	 * 
	 * @return roditelji
	 */
	List<Roditelj> dohvatiRoditelje();

	/**
	 * Dohvaća sve predmete.
	 * 
	 * @return predmeti
	 */
	List<Predmet> dohvatiPredmete();

	/**
	 * Dohvaća predmet.
	 * 
	 * @param id
	 *            ID predmeta
	 * @return predmet
	 */
	Predmet dohvatiPredmet(long id);

	/**
	 * Spaja profesora i predmet.
	 * 
	 * @param idPredmet
	 *            ID predmeta.
	 * @param emailProfesor
	 *            e-mail profesora
	 */
	void spojiPredmetIProfesora(long idPredmet, String emailProfesor);

	/**
	 * Dohvaća sve razrede.
	 * 
	 * @return razredi
	 */
	List<Razred> dohvatiRazrede();

	/**
	 * Dohvaća razred
	 * 
	 * @param id
	 *            ID razreda
	 * @return razred
	 */
	Razred dohvatiRazred(long id);

	/**
	 * Spaja razred i predmet
	 * 
	 * @param idRazred
	 *            ID razreda
	 * @param idPredmet
	 *            ID predmeta
	 */
	void spojiRazredIPredmet(long idRazred, long idPredmet);

	/**
	 * Spaja razrednika i razred.
	 * 
	 * @param id
	 *            ID razreda
	 * @param mail
	 *            e-mail razrednika
	 * @param jeRazrednik
	 *            zastavica koja oznacuje da li je profesor razrednik ili zamjenik
	 *            razrednika
	 */
	void spojiRazredIRazrednika(long id, String mail, boolean jeRazrednik);

	/**
	 * Spaja učenika i razred.
	 * 
	 * @param id
	 *            ID razreda
	 * @param mail
	 *            e-mail učenika
	 */
	void spojiUcenikaIRazred(long id, String mail);

	/**
	 * Dohvaća izostanke iz baze podataka.
	 * 
	 * @param mail
	 *            mail učenika
	 * @return lista izostanka
	 */
	List<Izostanak> dohvatiIzostanke(String mail);

	/**
	 * Dohvaća izostanke iz baze podataka
	 * 
	 * @param id
	 *            id razreda
	 * @return lista izostanaka
	 */
	List<Izostanak> dohvatiIzostanke(long id);

	/**
	 * Dohvaća izostanak.
	 * 
	 * @param id
	 *            id izostanka
	 * @return izostanak
	 */
	Izostanak dohvatiIzostanak(long id);

	/**
	 * Dohvaća unose za određenog učenika.
	 * 
	 * @param ucenik
	 *            ucenik
	 * @return lista unosa
	 */
	List<Unos> dohvatiUnose(Ucenik ucenik);

	/**
	 * Dohvaća događaje iz dnevnika događaja.
	 * 
	 * @return lista događaja
	 */
	List<Dogadaj> dohvatiDogadaje();

	/**
	 * Dohvaća unos.
	 * 
	 * @param id
	 *            ID unosa
	 * @return unos
	 */
	Unos dohvatiUnos(long id);

	/**
	 * Ispituje postoji li administrator.
	 * 
	 * @return true ako administrator postoji, inače false
	 */
	boolean postojiAdmin();
}