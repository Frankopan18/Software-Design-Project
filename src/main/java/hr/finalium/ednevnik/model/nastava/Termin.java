/**
 * 
 */
package hr.finalium.ednevnik.model.nastava;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Model termina predavanja.
 * 
 * @author Janko
 *
 */
@Entity
public class Termin implements Comparable<Termin> {
	/** ID termina */
	@Id
	@GeneratedValue
	private long id;
	/** Dan u tjednu kada se održava nastava iz određenog predmeta */
	@Column(length = 20, nullable = false)
	private String dan;
	/** Redni broj sata u danu */
	@Column(nullable = false)
	private int sat;
	/** Mjesto održavanja */
	@Column(length = 30, nullable = false)
	private String mjesto;

	/**
	 * Konstruktor.
	 *
	 */
	public Termin() {
	}

	/**
	 * Konstruktor.
	 * 
	 * @param dan
	 *            dan
	 * @param sat
	 *            sat
	 * @param mjesto
	 *            mjesto održavanja
	 */
	public Termin(String dan, int sat, String mjesto) {
		this.dan = dan;
		this.sat = sat;
		this.mjesto = mjesto;
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
	 * @return dan
	 */
	public String getDan() {
		return dan;
	}

	/**
	 * Setter.
	 * 
	 * @param dan
	 *            dan
	 */
	public void setDan(String dan) {
		this.dan = dan;
	}

	/**
	 * Getter.
	 * 
	 * @return sat
	 */
	public int getSat() {
		return sat;
	}

	/**
	 * Setter.
	 * 
	 * @param sat
	 *            sat
	 */
	public void setSat(int sat) {
		this.sat = sat;
	}

	/**
	 * Getter.
	 * 
	 * @return mjesto održavanja
	 */
	public String getMjesto() {
		return mjesto;
	}

	/**
	 * Setter.
	 * 
	 * @param mjesto
	 *            mjesto održavanja
	 */
	public void setMjesto(String mjesto) {
		this.mjesto = mjesto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dan == null) ? 0 : dan.hashCode());
		result = prime * result + ((mjesto == null) ? 0 : mjesto.hashCode());
		result = prime * result + sat;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Termin)) {
			return false;
		}
		Termin other = (Termin) obj;
		if (dan == null) {
			if (other.dan != null) {
				return false;
			}
		} else if (!dan.equals(other.dan)) {
			return false;
		}
		if (mjesto == null) {
			if (other.mjesto != null) {
				return false;
			}
		} else if (!mjesto.equals(other.mjesto)) {
			return false;
		}
		if (sat != other.sat) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return dan + " " + sat + ". sat [" + (mjesto == null ? "nepoznato" : mjesto) + "]";
	}

	@Override
	public int compareTo(Termin o) {
		if (Integer.compare(danToInt(dan), danToInt(o.dan)) == 0) {
			return Integer.compare(sat, o.sat);
		}
		return Integer.compare(danToInt(dan), danToInt(o.dan));
	}

	/**
	 * Konvertira dan u tekstualnom obliku u njegov redni broj.
	 * 
	 * @param dan
	 *            dan u tjednu
	 * @return redni broj dana u tjednu
	 */
	private static int danToInt(String dan) {
		switch (dan.toLowerCase()) {
		case "ponedjeljak":
			return 1;
		case "utorak":
			return 2;
		case "srijeda":
			return 3;
		case "četvrtak":
			return 4;
		case "petak":
			return 5;
		default:
			return 0;
		}
	}
	
	public int getDanInt() {
		return danToInt(dan);
	}
}
