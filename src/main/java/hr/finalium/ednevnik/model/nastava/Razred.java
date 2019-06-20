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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import hr.finalium.ednevnik.model.roles.Profesor;
import hr.finalium.ednevnik.model.roles.Ucenik;

/**
 * Model podataka razreda.
 * 
 * @author Zlatko
 *
 */
@Entity
public class Razred implements Comparable<Razred> {
	/** Id razreda */
	@Id
	@GeneratedValue
	private long id;
	/** Oznaka rezreda */
	@Column(length = 7)
	private String oznaka;
	/** Školska godina */
	@Column
	private int godina;
	/** Učenici koji su u ovom razredu */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "razred")
	private Set<Ucenik> ucenici = new TreeSet<>();
	/** Razrednik */
	@OneToOne(mappedBy = "razred")
	private Profesor razrednik;
	/** Zamjenik razrednika */
	@OneToOne(optional = true, mappedBy = "razred")
	private Profesor zamjenikRazrednika;
	/** Predmeti koje sluša razred. */
	@ManyToMany(mappedBy = "razredi")
	private Set<Predmet> predmeti = new TreeSet<>();
	/** Vrijeme i mjesto roditeljskog sastanka*/
	@Column
	private String roditeljskiSastanak;

	/**
	 * Konstruktor.
	 * 
	 * @param oznaka
	 *            oznaka razreda
	 * @param godina
	 *            školska godina
	 */
	public Razred(String oznaka, int godina) {
		super();
		this.oznaka = oznaka;
		this.godina = godina;
		this.roditeljskiSastanak = "/";
	}

	/**
	 * Konstruktor.
	 * 
	 */
	public Razred() {
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
	 * @return oznaka
	 */
	public String getOznaka() {
		return oznaka;
	}

	/**
	 * Setter.
	 * 
	 * @param oznaka
	 *            oznaka
	 */
	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	/**
	 * Getter.
	 * 
	 * @return godina
	 */
	public int getGodina() {
		return godina;
	}

	/**
	 * Setter.
	 * 
	 * @param godina
	 *            godina
	 */
	public void setGodina(int godina) {
		this.godina = godina;
	}

	/**
	 * Getter.
	 * 
	 * @return ucenici
	 */
	public Set<Ucenik> getUcenici() {
		return ucenici;
	}

	/**
	 * Setter.
	 * 
	 * @param ucenici
	 *            ucenici
	 */
	public void setUcenici(Set<Ucenik> ucenici) {
		this.ucenici = ucenici;
	}

	/**
	 * Getter.
	 * 
	 * @return razrednik
	 */
	public Profesor getRazrednik() {
		return razrednik;
	}

	/**
	 * Setter.
	 * 
	 * @param razrednik
	 *            razrednik
	 */
	public void setRazrednik(Profesor razrednik) {
		this.razrednik = razrednik;
	}

	/**
	 * Getter.
	 * 
	 * @return zamjenikRazrednika
	 */
	public Profesor getZamjenikRazrednika() {
		return zamjenikRazrednika;
	}

	/**
	 * Setter.
	 * 
	 * @param zamjenikRazrednika
	 *            zamjenikRazrednika
	 */
	public void setZamjenikRazrednika(Profesor zamjenikRazrednika) {
		this.zamjenikRazrednika = zamjenikRazrednika;
	}

	/**
	 * Getter.
	 * 
	 * @return predmeti
	 */
	public Set<Predmet> getPredmeti() {
		return predmeti;
	}

	/**
	 * Setter.
	 * 
	 * @param predmeti
	 *            predmeti
	 */
	public void setPredmeti(Set<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	/**	Getter. 
	 * @return roditeljskiSastanak
	 */
	public String getRoditeljskiSastanak() {
		return roditeljskiSastanak;
	}

	/**Setter.
	 * @param roditeljskiSastanak roditeljskiSastanak
	 */
	public void setRoditeljskiSastanak(String roditeljskiSastanak) {
		this.roditeljskiSastanak = roditeljskiSastanak;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Razred)) {
			return false;
		}
		Razred other = (Razred) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Razred o) {
		return oznaka.compareTo(o.oznaka);
	}

	@Override
	public String toString() {
		return oznaka;
	}
}
