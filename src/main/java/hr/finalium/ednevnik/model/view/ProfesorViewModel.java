package hr.finalium.ednevnik.model.view;

import hr.finalium.ednevnik.model.roles.Profesor;

/**
 * Model podataka profesora koji se prikazuje korisniku. Sadrži poruke pogreške
 * za svako polje koje se prikazuju korisniku ako je unio nelegalnu vrijednost.
 * 
 * @author Janko
 *
 */
public class ProfesorViewModel {
	/** E-mail */
	private String mail;
	/** Polje u koje se upisuje poruka ako je e-mail nelegalan */
	private String mailGreska;
	/** Hash vrijednost lozinke */
	private int lozinka;
	/** Hash vrijednost potvrde lozinke */
	private int lozinkaPotvrda;
	/** Polje u koje se upisuje poruka ako lozinka nelegalna */
	private String lozinkaGreska;
	/** Ime profesora */
	private String ime;
	/** Polje u koje se upisuje poruka ako ime nije valjano */
	private String imeGreska;
	/** Prezime profesora */
	private String prezime;
	/** Polje u koje se upisuje poruka ako prezime nije valjano */
	private String prezimeGreska;
	/** OIB profesora */
	private String oib;
	/** Polje u koje se upisuje poruka ako OIB nije valjan */
	private String oibGreska;
	/** Broj telefona profesora */
	private String brojTelefona;
	/** Polje u koje se upisuje poruka ako broj telefona nije valjan */
	private String brojTelefonaGreska;

	/**
	 * Konstruktor.
	 */
	public ProfesorViewModel() {
		ime = "";
		prezime = "";
		mail = "";
	}

	/**
	 * Konstruktor.
	 * 
	 * @param profesor
	 *            model profesora čiji je ovo view
	 */
	public ProfesorViewModel(Profesor profesor) {
		mail = profesor.getMail();
		lozinka = profesor.getLozinka();
		lozinkaPotvrda = profesor.getLozinka();
		ime = profesor.getIme();
		prezime = profesor.getPrezime();
		oib = profesor.getOib();
		brojTelefona = profesor.getBrojTelefona();
	}

	/**
	 * Konstruktor.
	 * 
	 * @param ime
	 *            ime profesora
	 * @param prezime
	 *            prezime profesora
	 * @param mail
	 *            e-mail profesora
	 * @param lozinka
	 *            lozinka profesora
	 * @param lozinkaPotvrda
	 *            potvrda lozinke profesora
	 * @param oib
	 *            OIB profesora
	 * @param brojTelefona
	 *            broj telefona profesora
	 */
	public ProfesorViewModel(String ime, String prezime, String mail, int lozinka, int lozinkaPotvrda, String oib,
			String brojTelefona) {
		this.ime = ime;
		if (ime == null || ime.isEmpty()) {
			imeGreska = "Ime ne smije biti prazno";
		}
		this.prezime = prezime;
		if (prezime == null || prezime.isEmpty()) {
			prezimeGreska = "Prezime ne smije biti prazno";
		}
		this.mail = mail;
		this.oib = oib;
		if (oib == null || oib.length() != 11 || oib.chars().anyMatch(c -> !Character.isDigit(c))) {
			oibGreska = "Oib mora biti 11-znamenkasti broj";
		}
		this.brojTelefona = brojTelefona;
		if (brojTelefona == null || brojTelefona.chars().anyMatch(c -> !Character.isDigit(c))) {
			brojTelefonaGreska = "Broj telefona ne smije biti prazan i mora sadržavati samo znamenke";
		}
		if (lozinka != lozinkaPotvrda) {
			lozinkaGreska = "Lozinka se ne podudaraju";
		} else {
			this.lozinka = lozinka;
			this.lozinkaPotvrda = lozinkaPotvrda;
		}
	}

	/**
	 * Provjerava jesu li uneseni podatci valjani.
	 * 
	 * @return true ako su podatci valjani, inače false
	 */
	public boolean podaciKonzistentni() {
		return lozinkaGreska == null && imeGreska == null && prezimeGreska == null && oibGreska == null
				&& brojTelefonaGreska == null && mailGreska == null;
	}

	/**
	 * Getter.
	 * 
	 * @return mail profesora
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Setter.
	 * 
	 * @param mail
	 *            mail profesora
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Getter.
	 * 
	 * @return ime profesora
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * Setter.
	 * 
	 * @param ime
	 *            ime profesora
	 */
	public void setIme(String ime) {
		this.ime = ime;
		if (ime == null || ime.isEmpty()) {
			imeGreska = "Ime ne smije biti prazno";
		} else {
			imeGreska = null;
		}
	}

	/**
	 * Getter.
	 * 
	 * @return poruka vezana uz grešku s imenom
	 */
	public String getImeGreska() {
		return imeGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param imeGreska
	 *            poruka vezana uz grešku s imenom
	 */
	public void setImeGreska(String imeGreska) {
		this.imeGreska = imeGreska;
	}

	/**
	 * Getter.
	 * 
	 * @return prezime profesora
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * Setter.
	 * 
	 * @param prezime
	 *            prezime profesora
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
		if (prezime == null || prezime.isEmpty()) {
			prezimeGreska = "Prezime ne smije biti prazno";
		} else {
			prezimeGreska = null;
		}
	}

	/**
	 * Getter.
	 * 
	 * @return poruka vezana uz grešku s prezimenom
	 */
	public String getPrezimeGreska() {
		return prezimeGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param prezimeGreska
	 *            poruka vezana uz grešku s prezimenom
	 */
	public void setPrezimeGreska(String prezimeGreska) {
		this.prezimeGreska = prezimeGreska;
	}

	/**
	 * Getter.
	 * 
	 * @return OIB profesora
	 */
	public String getOib() {
		return oib;
	}

	/**
	 * Setter.
	 * 
	 * @param oib
	 *            OIB profesora
	 */
	public void setOib(String oib) {
		this.oib = oib;
		if (oib == null || oib.length() != 11 || oib.chars().anyMatch(c -> !Character.isDigit(c))) {
			oibGreska = "Oib mora biti 11-znamenkasti broj";
		} else {
			oibGreska = null;
		}
	}

	/**
	 * Getter.
	 * 
	 * @return poruka vezana uz grešku s OIB-om
	 */
	public String getOibGreska() {
		return oibGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param oibGreska
	 *            poruka vezana uz grešku s OIB-om
	 */
	public void setOibGreska(String oibGreska) {
		this.oibGreska = oibGreska;
	}

	/**
	 * Getter.
	 * 
	 * @return broj telefona profesora
	 */
	public String getBrojTelefona() {
		return brojTelefona;
	}

	/**
	 * Setter.
	 * 
	 * @param brojTelefona
	 *            broj telefona profesora
	 */
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
		if (brojTelefona == null || oib.chars().anyMatch(c -> !Character.isDigit(c))) {
			brojTelefonaGreska = "Broj telefona ne smije biti prazan i mora sadržavati samo znamenke";
		} else {
			brojTelefonaGreska = null;
		}
	}

	/**
	 * Getter.
	 * 
	 * @return poruka vezana uz grešku s brojem telefona
	 */
	public String getBrojTelefonaGreska() {
		return brojTelefonaGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param brojTelefonaGreska
	 *            poruka vezana uz grešku s brojem telefona
	 */
	public void setBrojTelefonaGreska(String brojTelefonaGreska) {
		this.brojTelefonaGreska = brojTelefonaGreska;
	}

	/**
	 * Getter.
	 * 
	 * @return hash vrijednost lozinke profesora
	 */
	public int getLozinka() {
		return lozinka;
	}

	/**
	 * Setter.
	 * 
	 * @param lozinka
	 *            hash vrijednost lozinke profesora
	 */
	public void setLozinka(int lozinka) {
		this.lozinka = lozinka;
		if (lozinka != lozinkaPotvrda) {
			lozinkaGreska = "Lozinke se ne podudaraju";
		} else {
			lozinkaGreska = null;
		}
	}

	/**
	 * Getter.
	 * 
	 * @return hash vrijednost potvrde lozinke profesora
	 */
	public int getLozinkaPotvrda() {
		return lozinkaPotvrda;
	}

	/**
	 * Setter.
	 * 
	 * @param lozinkaPotvrda
	 *            hash vrijednost potvrde lozinke profesora
	 */
	public void setLozinkaPotvrda(int lozinkaPotvrda) {
		this.lozinkaPotvrda = lozinkaPotvrda;
		if (lozinka != lozinkaPotvrda) {
			lozinkaGreska = "Lozinke se ne podudaraju";
		} else {
			lozinkaGreska = null;
		}
	}

	/**
	 * Getter.
	 * 
	 * @return poruka vezana uz grešku s e-mailom
	 */
	public String getMailGreska() {
		return mailGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param mailError
	 *            poruka vezana uz grešku s e-mailom
	 */
	public void setMailGreska(String mailError) {
		this.mailGreska = mailError;
	}

	/**
	 * Getter.
	 * 
	 * @return poruka koja ukazuje na problem s lozinkom
	 */
	public String getLozinkaGreska() {
		return lozinkaGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param lozinkaGreska
	 *            poruka koja ukazuje na problem s lozinkom
	 */
	public void setLozinkaGreska(String lozinkaGreska) {
		this.lozinkaGreska = lozinkaGreska;
	}
}
