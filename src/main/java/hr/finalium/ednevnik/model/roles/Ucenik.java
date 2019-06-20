package hr.finalium.ednevnik.model.roles;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import hr.finalium.ednevnik.model.nastava.Razred;

/**
 * Model podataka učenika.
 * 
 * @author Zlatko
 *
 */
@Entity
public class Ucenik extends Korisnik implements Comparable<Ucenik> {
	/** Ime učenika */
	@Column(nullable = false)
	private String ime;
	/** Prezime učenika */
	@Column(nullable = false)
	private String prezime;
	/** OIB učenika */
	@Column(nullable = false, length = 11, unique = true)
	private String oib;
	/** Datum rođenja učenika */
	@Column(nullable = false)
	private String datumRodenja;
	/** Mjesto rođenja učenika */
	@Column(nullable = false)
	private String mjestoRodenja;
	/** Roditelji učenika */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	private Set<Roditelj> roditelji = new HashSet<>();
	/** Razred u kojem je učenik. */
	@ManyToOne
	private Razred razred;
	/** Spol učenika. */
	@Enumerated
	private Spol spol;
	/** Slika učenika. */
	@Lob
	@Column(length = 10_000_000)
	private byte[] slika;

	/**
	 * Konstruktor.
	 *
	 */
	public Ucenik() {
	}

	/**
	 * Konstruktor.
	 * 
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
	 * @param mail
	 *            e-mail adresa učenika
	 * @param hashLozinka
	 *            hash vrijednost lozinke učenika
	 */
	public Ucenik(String ime, String prezime, String oib, String datumRodenja, String mjestoRodenja, Spol spol,
			byte[] slika, String mail, int hashLozinka) {
		super(mail, hashLozinka);
		this.ime = ime;
		this.prezime = prezime;
		this.oib = oib;
		this.datumRodenja = datumRodenja;
		this.mjestoRodenja = mjestoRodenja;
		this.spol = spol;
		this.slika = slika;
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
	}

	/**
	 * Getter.
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
	}

	/**
	 * Getter.
	 * 
	 * @return roditelji
	 */
	public Set<Roditelj> getRoditelji() {
		return roditelji;
	}

	/**
	 * Setter.
	 * 
	 * @param roditelji
	 *            roditelji
	 */
	public void setRoditelji(Set<Roditelj> roditelji) {
		this.roditelji = roditelji;
	}

	/**
	 * Getter.
	 * 
	 * @return razred
	 */
	public Razred getRazred() {
		return razred;
	}

	/**
	 * Setter.
	 * 
	 * @param razred
	 *            razred
	 */
	public void setRazred(Razred razred) {
		this.razred = razred;
	}

	/**
	 * Getter.
	 * 
	 * @return spol
	 */
	public Spol getSpol() {
		return spol;
	}

	/**
	 * Setter.
	 * 
	 * @param spol
	 *            spol
	 */
	public void setSpol(Spol spol) {
		this.spol = spol;
	}

	/**
	 * Getter.
	 * 
	 * @return slika
	 */
	public byte[] getSlika() {
		return slika;
	}

	/**
	 * Setter.
	 * 
	 * @param slika
	 *            slika
	 */
	public void setSlika(byte[] slika) {
		this.slika = slika;
	}

	@Override
	public int compareTo(Ucenik o) {
		if (prezime.compareToIgnoreCase(o.prezime) == 0) {
			return ime.compareToIgnoreCase(ime);
		}
		return prezime.compareToIgnoreCase(o.prezime);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Ucenik)) {
			return false;
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return this.ime + " " + this.prezime;
	}
}
