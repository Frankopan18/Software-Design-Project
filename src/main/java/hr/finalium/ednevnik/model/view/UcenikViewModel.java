package hr.finalium.ednevnik.model.view;

import hr.finalium.ednevnik.model.roles.Spol;
import hr.finalium.ednevnik.model.roles.Ucenik;

/**
 * Model podataka učenika koji se prikazuje korisniku. Sadrži poruke pogreške za
 * svako polje koje se prikazuju korisniku ako je unio nelegalnu vrijednost.
 * 
 * @author Zlatko
 *
 */
public class UcenikViewModel {
	/** E-mail */
	private String mail;
	/** Poruka vezana uz gresku s mailom */
	private String mailGreska;
	/** Hash vrijednost lozinke */
	private int lozinka;
	/** Hash vrijednost potvrde lozinke */
	private int lozinkaPotvrda;
	/** Poruka vezana uz grešku s lozinkom */
	private String lozinkaGreska;
	/** Ime */
	private String ime;
	/** Poruka vezana uz grešku s imenom */
	private String imeGreska;
	/** Prezime */
	private String prezime;
	/** Poruka vezana uz grešku s prezimenom */
	private String prezimeGreska;
	/** OIB */
	private String oib;
	/** Poruka vezana uz grešku s OIB-om */
	private String oibGreska;
	/** Datum rođenja */
	private String datumRodenja;
	/** Poruka vezana uz grešku s datumom rođenja */
	private String datumRodenjaGreska;
	/** Mjesto rođenja */
	private String mjestoRodenja;
	/** Poruka vezana uz grešku s mjestom rođenja */
	private String mjestoRodenjaGreska;
	/** Spol učenika*/
	private Spol spol;
	/** Poruka vezana uz grešku kod unosa spola*/
	private String spolGreska;
	/** Slika učenika*/
	private byte[] slika;

	/**
	 * Konstruktor.
	 *
	 */
	public UcenikViewModel() {
	}

	/**
	 * Konstruktor.
	 * 
	 * @param ucenik
	 *            model učenika čiji je ovo view
	 */
	public UcenikViewModel(Ucenik ucenik) {
		mail = ucenik.getMail();
		ime = ucenik.getIme();
		prezime = ucenik.getPrezime();
		oib = ucenik.getOib();
		datumRodenja = ucenik.getDatumRodenja();
		mjestoRodenja = ucenik.getMjestoRodenja();
		spol = ucenik.getSpol();
		slika = ucenik.getSlika();
		setLozinka(ucenik.getLozinka());
		setLozinkaPotvrda(ucenik.getLozinka());
	}

	/**
	 * Konstruktor.
	 * 
	 * @param mail
	 *            e-mail učenika
	 * @param lozinka
	 *            hash vrijednost lozinke učenika
	 * @param lozinkaPotvrda
	 *            hash vrijednost potvrde lozinke učenika
	 * @param ime
	 *            ime učenika
	 * @param prezime
	 *            prezime učenika
	 * @param oib
	 *            OIB učenika
	 * @param datumRodenja
	 *            datum rođenja učenika
	 * @param mjestoRodenja
	 *            mjesto rođenja učenika
	 */
	public UcenikViewModel(String mail, int lozinka, int lozinkaPotvrda, String ime, String prezime, String oib,
			String datumRodenja, String mjestoRodenja, Spol spol, byte[] slika) {
		super();
		if (lozinka != lozinkaPotvrda) {
			setLozinkaGreska("Lozinke se ne podudaraju");
		} else {
			this.lozinka = lozinka;
			this.lozinkaPotvrda = lozinkaPotvrda;
		}
		this.mail = mail;
		this.ime = ime;
		if (ime == null || ime.isEmpty()) {
			imeGreska = "Ime ne smije biti prazno";
		}
		this.prezime = prezime;
		if (prezime == null || prezime.isEmpty()) {
			prezimeGreska = "Prezime ne smije biti prazno";
		}
		this.oib = oib;
		if (oib == null || oib.length() != 11 || oib.chars().anyMatch(c -> !Character.isDigit(c))) {
			oibGreska = "OIB mora biti 11 znamenkasti broj";
		}
		this.datumRodenja = datumRodenja;
		if (datumRodenja == null || datumRodenja.isEmpty()) {
			datumRodenjaGreska = "Datum rodenja ne smije biti prazan";
		}
		this.mjestoRodenja = mjestoRodenja;
		if (mjestoRodenja == null || mjestoRodenja.isEmpty()) {
			mjestoRodenjaGreska = "Mjesto rodenja ne smije biti prazno";
		}
		this.spol = spol;
		if(spol == Spol.NEPOZNATO){
			spolGreska = "Spol ne smije biti nepoznat";
		}
		this.slika = slika;
	}

	/**
	 * Provjerava jesu li uneseni podacti valjani.
	 * 
	 * @return true ako su podatci valjani, inače false
	 */
	public boolean podaciKonzistentni() {
		return imeGreska == null && prezimeGreska == null && oibGreska == null && datumRodenjaGreska == null
				&& mjestoRodenjaGreska == null && lozinkaGreska == null && mailGreska == null && spolGreska == null;
	}

	/**
	 * Getter.
	 * 
	 * @return e-mail učenika
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Setter.
	 * 
	 * @param mail
	 *            e-mail učenika
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Getter.
	 * 
	 * @return ime učenika
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * Setter.
	 * 
	 * @param ime
	 *            ime učenika
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
	 * @return prezime učenika
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * Setter.
	 * 
	 * @param prezime
	 *            prezime učenika
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
	 * @return poruka vezana uz grešku s prezimenom učenika
	 */
	public String getPrezimeGreska() {
		return prezimeGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param prezimeGreska
	 *            poruka vezana uz grešku s prezimenom učenika
	 */
	public void setPrezimeGreska(String prezimeGreska) {
		this.prezimeGreska = prezimeGreska;
	}

	/**
	 * Getter
	 * 
	 * @return OIB učenika
	 */
	public String getOib() {
		return oib;
	}

	/**
	 * Setter.
	 * 
	 * @param oib
	 *            OIB učenika
	 */
	public void setOib(String oib) {
		this.oib = oib;
		if (oib == null || oib.length() != 11 || oib.chars().anyMatch(c -> !Character.isDigit(c))) {
			oibGreska = "OIB mora biti 11 znamenkasti broj";
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
	 * @return datum rođenja učenika
	 */
	public String getDatumRodenja() {
		return datumRodenja;
	}

	/**
	 * Setter.
	 * 
	 * @param datumRodenja
	 *            datum rođenja učenika
	 */
	public void setDatumRodenja(String datumRodenja) {
		this.datumRodenja = datumRodenja;
		if (datumRodenja == null || datumRodenja.isEmpty()) {
			datumRodenjaGreska = "Datum rodenja ne smije biti prazan";
		} else {
			datumRodenjaGreska = null;
		}
	}

	/**
	 * Getter.
	 * 
	 * @return poruka vezana uz grešku s datumom rođenja učenika
	 */
	public String getDatumRodenjaGreska() {
		return datumRodenjaGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param datumRodenjaGreska
	 *            poruka vezana uz grešku s datumom rođenja učenika
	 */
	public void setDatumRodenjaGreska(String datumRodenjaGreska) {
		this.datumRodenjaGreska = datumRodenjaGreska;
	}

	/**
	 * Getter.
	 * 
	 * @return mjesto rođenja učenika
	 */
	public String getMjestoRodenja() {
		return mjestoRodenja;
	}

	/**
	 * Setter.
	 * 
	 * @param mjestoRodenja
	 *            mjesto rođenja učenika
	 */
	public void setMjestoRodenja(String mjestoRodenja) {
		this.mjestoRodenja = mjestoRodenja;
		if (mjestoRodenja == null || mjestoRodenja.isEmpty()) {
			mjestoRodenjaGreska = "Mjesto rodenja ne smije biti prazno";
		} else {
			this.mjestoRodenjaGreska = null;
		}
	}

	/**
	 * Getter.
	 * 
	 * @return poruka vezana uz grešku s mjestom rođenja učenika
	 */
	public String getMjestoRodenjaGreska() {
		return mjestoRodenjaGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param mjestoRodenjaGreska
	 *            poruka vezana uz grešku s mjestom rođenja učenika
	 */
	public void setMjestoRodenjaGreska(String mjestoRodenjaGreska) {
		this.mjestoRodenjaGreska = mjestoRodenjaGreska;
	}

	/**
	 * Getter.
	 * 
	 * @return hash vrijednost lozinke učenika
	 */
	public int getLozinka() {
		return lozinka;
	}

	/**
	 * Setter.
	 * 
	 * @param lozinka
	 *            hash vrijednost lozinke učenika
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
	 * @return hash vrijednost potvrde lozinke učenika
	 */
	public int getLozinkaPotvrda() {
		return lozinkaPotvrda;
	}

	/**
	 * Setter.
	 * 
	 * @param lozinkaPotvrda
	 *            hash vrijednost potvrde lozinke učenika
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
	 * @return poruka vezana uz grešku s lozinkom učenika
	 */
	public String getLozinkaGreska() {
		return lozinkaGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param lozinkaGreska
	 *            poruka vezana uz grešku s lozinkom učenika
	 */
	public void setLozinkaGreska(String lozinkaGreska) {
		this.lozinkaGreska = lozinkaGreska;
	}

	/**
	 * Getter.
	 * 
	 * @return poruka vezana uz gresku s mailom
	 */
	public String getMailGreska() {
		return mailGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param mailGreska
	 *            poruka vezana uz gresku s mailom
	 */
	public void setMailGreska(String mailGreska) {
		this.mailGreska = mailGreska;
	}

	/**	Getter. 
	 * @return spol
	 */
	public Spol getSpol() {
		return spol;
	}

	/**Setter.
	 * @param spol spol
	 */
	public void setSpol(Spol spol) {
		this.spol = spol;
		if(spol == Spol.NEPOZNATO){
			spolGreska = "Spol ne smije biti nepoznat";
		}
	}

	/**	Getter. 
	 * @return spolGreska
	 */
	public String getSpolGreska() {
		return spolGreska;
	}

	/**Setter.
	 * @param spolGreska spolGreska
	 */
	public void setSpolGreska(String spolGreska) {
		this.spolGreska = spolGreska;
	}

	/**	Getter. 
	 * @return slika
	 */
	public byte[] getSlika() {
		return slika;
	}

	/**Setter.
	 * @param slika slika
	 */
	public void setSlika(byte[] slika) {
		this.slika = slika;
	}
}
