/**
 * 
 */
package hr.finalium.ednevnik.model.view;

import hr.finalium.ednevnik.model.roles.Roditelj;

/**
 * @author Janko
 *
 */
public class RoditeljViewModel {

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
	/** Broj telefona profesora */
	private String brojTelefona;
	/** Polje u koje se upisuje poruka ako broj telefona nije valjan */
	private String brojTelefonaGreska;

	/**
	 * Konstruktor.
	 *
	 */
	public RoditeljViewModel() {
		ime = "";
		prezime = "";
		mail = "";
	}

	/**
	 * Konstruktor.
	 * 
	 * @param roditelj
	 *            roditelj po kojem se radi view model
	 */
	public RoditeljViewModel(Roditelj roditelj) {
		mail = roditelj.getMail();
		lozinka = roditelj.getLozinka();
		lozinkaPotvrda = roditelj.getLozinka();
		ime = roditelj.getIme();
		prezime = roditelj.getPrezime();
		brojTelefona = roditelj.getBrojTelefona();
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
	 * @param brojTelefona
	 *            broj telefona profesora
	 */
	public RoditeljViewModel(String ime, String prezime, String mail, int lozinka, int lozinkaPotvrda,
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
		return lozinkaGreska == null && imeGreska == null && prezimeGreska == null && brojTelefonaGreska == null
				&& mailGreska == null;
	}

	/**
	 * Getter.
	 * 
	 * @return mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Setter.
	 * 
	 * @param mail
	 *            mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Getter.
	 * 
	 * @return mailGreska
	 */
	public String getMailGreska() {
		return mailGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param mailGreska
	 *            mailGreska
	 */
	public void setMailGreska(String mailGreska) {
		this.mailGreska = mailGreska;
	}

	/**
	 * Getter.
	 * 
	 * @return lozinka
	 */
	public int getLozinka() {
		return lozinka;
	}

	/**
	 * Setter.
	 * 
	 * @param lozinka
	 *            lozinka
	 */
	public void setLozinka(int lozinka) {
		this.lozinka = lozinka;
	}

	/**
	 * Getter.
	 * 
	 * @return lozinkaPotvrda
	 */
	public int getLozinkaPotvrda() {
		return lozinkaPotvrda;
	}

	/**
	 * Setter.
	 * 
	 * @param lozinkaPotvrda
	 *            lozinkaPotvrda
	 */
	public void setLozinkaPotvrda(int lozinkaPotvrda) {
		this.lozinkaPotvrda = lozinkaPotvrda;
	}

	/**
	 * Getter.
	 * 
	 * @return lozinkaGreska
	 */
	public String getLozinkaGreska() {
		return lozinkaGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param lozinkaGreska
	 *            lozinkaGreska
	 */
	public void setLozinkaGreska(String lozinkaGreska) {
		this.lozinkaGreska = lozinkaGreska;
	}

	/**
	 * Getter.
	 * 
	 * @return ime
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * Setter.
	 * 
	 * @param ime
	 *            ime
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}

	/**
	 * Getter.
	 * 
	 * @return imeGreska
	 */
	public String getImeGreska() {
		return imeGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param imeGreska
	 *            imeGreska
	 */
	public void setImeGreska(String imeGreska) {
		this.imeGreska = imeGreska;
	}

	/**
	 * Getter.
	 * 
	 * @return prezime
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * Setter.
	 * 
	 * @param prezime
	 *            prezime
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	/**
	 * Getter.
	 * 
	 * @return prezimeGreska
	 */
	public String getPrezimeGreska() {
		return prezimeGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param prezimeGreska
	 *            prezimeGreska
	 */
	public void setPrezimeGreska(String prezimeGreska) {
		this.prezimeGreska = prezimeGreska;
	}

	/**
	 * Getter.
	 * 
	 * @return brojTelefona
	 */
	public String getBrojTelefona() {
		return brojTelefona;
	}

	/**
	 * Setter.
	 * 
	 * @param brojTelefona
	 *            brojTelefona
	 */
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	/**
	 * Getter.
	 * 
	 * @return brojTelefonaGreska
	 */
	public String getBrojTelefonaGreska() {
		return brojTelefonaGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param brojTelefonaGreska
	 *            brojTelefonaGreska
	 */
	public void setBrojTelefonaGreska(String brojTelefonaGreska) {
		this.brojTelefonaGreska = brojTelefonaGreska;
	}
}
