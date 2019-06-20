package hr.finalium.ednevnik.model.roles;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import hr.finalium.ednevnik.model.nastava.Predmet;
import hr.finalium.ednevnik.model.nastava.Razred;

/**
 * Model podataka profesora.
 * 
 * @author Zlatko
 *
 */
@Entity
public class Profesor extends Korisnik implements Comparable<Profesor> {
	/** Ime profesora */
	@Column(nullable = false)
	private String ime;
	/** Prezime profesora */
	@Column(nullable = false)
	private String prezime;
	/** OIB profesora */
	@Column(nullable = false, length = 11, unique = true)
	private String oib;
	/** Broj telefona profesora */
	@Column(nullable = false)
	private String brojTelefona;
	/** Predmeti koje profesor predaje */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	private Set<Predmet> predmeti = new HashSet<>();
	/** Razred kojem je profesor razrednik ili zamjenik razrednika */
	@OneToOne
	private Razred razred;
	/** Zastavica koja određuje je li profesor razrednik ili zamjenik razrednika */
	@Column
	private boolean razrednik;
	/** Termin konzultacija */
	@Column
	private String konzultacije;

	/**
	 * Konstruktor.
	 *
	 */
	public Profesor() {
	}

	/**
	 * Konstruktor.
	 * 
	 * @param ime
	 *            ime profesora
	 * @param prezime
	 *            prezime profesora
	 * @param oib
	 *            oib profesora
	 * @param brojTelefona
	 *            broj telefona profesora
	 * @param mail
	 *            mail adresa profesora
	 * @param lozinka
	 *            hash vrijednost lozinke profesora
	 */
	public Profesor(String ime, String prezime, String oib, String brojTelefona, String mail, int lozinka) {
		super(mail, lozinka);
		this.ime = ime;
		this.prezime = prezime;
		this.oib = oib;
		this.brojTelefona = brojTelefona;
		this.konzultacije = "/";
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
	}

	/**
	 * Getter.
	 * 
	 * @return predmeti koje profesor predaje
	 */
	public Set<Predmet> getPredmeti() {
		return predmeti;
	}

	/**
	 * Setter.
	 * 
	 * @param predmeti
	 *            predmeti koje profesor predaje
	 */
	public void setPredmeti(Set<Predmet> predmeti) {
		this.predmeti = predmeti;
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
	 * @return razrednik
	 */
	public boolean isRazrednik() {
		return razrednik;
	}

	/**
	 * Setter.
	 * 
	 * @param razrednik
	 *            razrednik
	 */
	public void setRazrednik(boolean razrednik) {
		this.razrednik = razrednik;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Profesor)) {
			return false;
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Profesor o) {
		if (prezime.compareToIgnoreCase(o.prezime) == 0) {
			return ime.compareToIgnoreCase(o.ime);
		}
		return prezime.compareToIgnoreCase(o.prezime);
	}

	/**
	 * Getter.
	 * 
	 * @return konzultacije
	 */
	public String getKonzultacije() {
		return konzultacije;
	}

	/**
	 * Setter.
	 * 
	 * @param konzultacije
	 *            konzultacije
	 */
	public void setKonzultacije(String konzultacije) {
		this.konzultacije = konzultacije;
	}
}
