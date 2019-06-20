/**
 * 
 */
package hr.finalium.ednevnik.model.nastava;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hr.finalium.ednevnik.model.roles.Profesor;
import hr.finalium.ednevnik.model.roles.Ucenik;

/**
 * @author Zlatko
 *
 */
@Entity
public class Unos implements Comparable<Unos> {
	/** ID unosa */
	@Id
	@GeneratedValue
	private long id;
	/** Profesor koji je kreirao unos */
	@OneToOne
	private Profesor profesor;
	/** Učenik za kojeg je unos kreiran */
	@OneToOne
	private Ucenik ucenik;
	/** Vrijeme kreiranja unosa */
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date vrijemeUnosa;
	/** Komentar */
	@Column(length = 100)
	private String komentar;
	/** Ocjena */
	@Column(nullable = true)
	private Integer ocjena;
	/** Kategorija unosa */
	@ManyToOne
	private Kategorija kategorija;

	/**
	 * Konstruktor.
	 * 
	 * @param ocjena
	 *            ocjena
	 * @param komentar
	 *            komentar
	 */
	public Unos(Integer ocjena, String komentar) {
		this.ocjena = ocjena;
		this.komentar = komentar;
	}

	/**
	 * Konstruktor.
	 * 
	 */
	public Unos() {
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
	 * @return profesor
	 */
	public Profesor getProfesor() {
		return profesor;
	}

	/**
	 * Setter.
	 * 
	 * @param profesor
	 *            profesor
	 */
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	/**
	 * Getter.
	 * 
	 * @return ucenik
	 */
	public Ucenik getUcenik() {
		return ucenik;
	}

	/**
	 * Setter.
	 * 
	 * @param ucenik
	 *            ucenik
	 */
	public void setUcenik(Ucenik ucenik) {
		this.ucenik = ucenik;
	}

	/**
	 * Getter.
	 * 
	 * @return vrijemeUnosa
	 */
	public Date getVrijemeUnosa() {
		return vrijemeUnosa;
	}

	/**
	 * Setter.
	 * 
	 * @param vrijemeUnosa
	 *            vrijemeUnosa
	 */
	public void setVrijemeUnosa(Date vrijemeUnosa) {
		this.vrijemeUnosa = vrijemeUnosa;
	}

	/**
	 * Getter.
	 * 
	 * @return komentar
	 */
	public String getKomentar() {
		return komentar;
	}

	/**
	 * Setter.
	 * 
	 * @param komentar
	 *            komentar
	 */
	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	/**
	 * Getter.
	 * 
	 * @return ocjena
	 */
	public Integer getOcjena() {
		return ocjena;
	}

	/**
	 * Setter.
	 * 
	 * @param ocjena
	 *            ocjena
	 */
	public void setOcjena(Integer ocjena) {
		this.ocjena = ocjena;
	}

	/**
	 * Getter.
	 * 
	 * @return kategorija
	 */
	public Kategorija getKategorija() {
		return kategorija;
	}

	/**
	 * Setter.
	 * 
	 * @param kategorija
	 *            kategorija
	 */
	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (!(obj instanceof Unos)) {
			return false;
		}
		Unos other = (Unos) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Unos o) {
		return vrijemeUnosa.compareTo(o.vrijemeUnosa);
	}

	@Override
	public String toString() {
		return ucenik + " " + komentar + " {ocjena " + (ocjena == null ? "/" : ocjena) + "}";
	}
}
