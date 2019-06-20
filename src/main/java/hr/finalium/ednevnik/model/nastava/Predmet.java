/**
 * 
 */
package hr.finalium.ednevnik.model.nastava;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import hr.finalium.ednevnik.model.roles.Profesor;

/**
 * Model predmeta.
 * 
 * @author Janko
 *
 */
@Entity
public class Predmet implements Comparable<Predmet> {
	/** Id predmeta */
	@Id
	@GeneratedValue
	private long id;
	/** Naziv predmeta */
	@Column(length = 50, nullable = false)
	private String naziv;
	/** Školska godina u kojoj se predmet predaje */
	@Column(nullable = false)
	private int skolskaGodina;
	/** Opis predmeta */
	@Column(length = 500, nullable = false)
	private String opis;
	/** Termini u kojima se predmet predaje */
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Termin> termini = new TreeSet<>();
	/** Profesori koji predaju predmet */
	@ManyToMany(mappedBy = "predmeti", fetch = FetchType.EAGER)
	private Set<Profesor> profesori = new TreeSet<>();
	/** PDF dokument */
	@Lob
	@Column(length=10_000_000)
	private byte[] nastavniPlan;
	/** PDF dokument */
	@Lob
	@Column(length=10_000_000)
	private byte[] nastavnoPismo;
	/** Razredi koji slušaju predmet */
	@ManyToMany
	private Set<Razred> razredi = new TreeSet<>();
	/** Kategorije ocjena i unosa */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "predmet")
	private Set<Kategorija> kategorije = new TreeSet<>();

	/**
	 * Konstruktor.
	 *
	 */
	public Predmet() {
	}

	/**
	 * Konstruktor.
	 * 
	 * @param naziv
	 *            naziv predmeta
	 * @param skolskaGodina
	 *            skolska godina
	 * @param opis
	 *            opis predmeta
	 * @param nastavniPlan
	 *            nastavni plan
	 * @param nastavnoPismo
	 *            nastavno pismo
	 */
	public Predmet(String naziv, int skolskaGodina, String opis, byte[] nastavniPlan, byte[] nastavnoPismo) {
		this.naziv = naziv;
		this.skolskaGodina = skolskaGodina;
		this.opis = opis;
		this.nastavniPlan = nastavniPlan;
		this.nastavnoPismo = nastavnoPismo;
	}

	/**
	 * Getter.
	 * 
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter.
	 * 
	 * @param id
	 *            id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Getter.
	 * 
	 * @return naziv
	 */
	public String getNaziv() {
		return naziv;
	}

	/**
	 * Setter.
	 * 
	 * @param naziv
	 *            naziv
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	/**
	 * Getter.
	 * 
	 * @return skolskaGodina
	 */
	public int getSkolskaGodina() {
		return skolskaGodina;
	}

	/**
	 * Setter.
	 * 
	 * @param skolskaGodina
	 *            skolskaGodina
	 */
	public void setSkolskaGodina(int skolskaGodina) {
		this.skolskaGodina = skolskaGodina;
	}

	/**
	 * Getter.
	 * 
	 * @return opis
	 */
	public String getOpis() {
		return opis;
	}

	/**
	 * Setter.
	 * 
	 * @param opis
	 *            opis
	 */
	public void setOpis(String opis) {
		this.opis = opis;
	}

	/**
	 * Getter.
	 * 
	 * @return termini
	 */
	public Set<Termin> getTermini() {
		return termini;
	}

	/**
	 * Setter.
	 * 
	 * @param termini
	 *            termini
	 */
	public void setTermini(Set<Termin> termini) {
		this.termini = termini;
	}

	/**
	 * Getter.
	 * 
	 * @return profesori
	 */
	public Set<Profesor> getProfesori() {
		return profesori;
	}

	/**
	 * Setter.
	 * 
	 * @param profesori
	 *            profesori
	 */
	public void setProfesori(Set<Profesor> profesori) {
		this.profesori = profesori;
	}

	/**
	 * Getter.
	 * 
	 * @return nastavniPlan
	 */
	public byte[] getNastavniPlan() {
		return nastavniPlan;
	}

	/**
	 * Setter.
	 * 
	 * @param nastavniPlan
	 *            nastavniPlan
	 */
	public void setNastavniPlan(byte[] nastavniPlan) {
		this.nastavniPlan = nastavniPlan;
	}

	/**
	 * Getter.
	 * 
	 * @return nastavnoPismo
	 */
	public byte[] getNastavnoPismo() {
		return nastavnoPismo;
	}

	/**
	 * Setter.
	 * 
	 * @param nastavnoPismo
	 *            nastavnoPismo
	 */
	public void setNastavnoPismo(byte[] nastavnoPismo) {
		this.nastavnoPismo = nastavnoPismo;
	}

	/**
	 * Getter.
	 * 
	 * @return razredi koji slušaju predmet
	 */
	public Set<Razred> getRazredi() {
		return razredi;
	}

	/**
	 * Setter.
	 * 
	 * @param razredi
	 *            razredi koji slušaju predmet
	 */
	public void setRazredi(Set<Razred> razredi) {
		this.razredi = razredi;
	}

	/**
	 * Getter.
	 * 
	 * @return kategorije
	 */
	public Set<Kategorija> getKategorije() {
		return kategorije;
	}

	/**
	 * Setter.
	 * 
	 * @param kategorije
	 *            kategorije
	 */
	public void setKategorije(Set<Kategorija> kategorije) {
		this.kategorije = kategorije;
	}

	@Override
	public int hashCode() {
		return Long.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Predmet)) {
			return false;
		}
		Predmet other = (Predmet) obj;
		return this.id == other.id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Predmet o) {
		return naziv.compareToIgnoreCase(o.naziv);
	}

	@Override
	public String toString() {
		return this.naziv;
	}
}
