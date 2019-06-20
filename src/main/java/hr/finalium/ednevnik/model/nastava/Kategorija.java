/**
 * 
 */
package hr.finalium.ednevnik.model.nastava;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Zlatko
 *
 */
@Entity
public class Kategorija implements Comparable<Kategorija> {
	/** ID kategorije */
	@Id
	@GeneratedValue
	private long id;
	/** Naziv kategorije */
	@Column(length = 20, nullable = false)
	private String naziv;
	/** Unosi u ovoj kategoriji */
	@OneToMany
	private Set<Unos> unosi;
	/** Predmet */
	@ManyToOne
	private Predmet predmet;

	/**
	 * Konstruktor.
	 * 
	 */
	public Kategorija() {
	}

	/**
	 * Konstruktor.
	 * 
	 * @param naziv
	 *            naziv kategorije
	 */
	public Kategorija(String naziv) {
		this.naziv = naziv;
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
	 * @return unosi
	 */
	public Set<Unos> getUnosi() {
		return unosi;
	}

	/**
	 * Setter.
	 * 
	 * @param unosi
	 *            unosi
	 */
	public void setUnosi(Set<Unos> unosi) {
		this.unosi = unosi;
	}

	/**
	 * Getter.
	 * 
	 * @return predmet
	 */
	public Predmet getPredmet() {
		return predmet;
	}

	/**
	 * Setter.
	 * 
	 * @param predmet
	 *            predmet
	 */
	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
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
		if (!(obj instanceof Kategorija)) {
			return false;
		}
		Kategorija other = (Kategorija) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Kategorija o) {
		return naziv.compareTo(o.naziv);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return naziv;
	}
}
