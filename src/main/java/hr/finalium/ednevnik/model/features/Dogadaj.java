/**
 * 
 */
package hr.finalium.ednevnik.model.features;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Model događaja dnevnika događaja.
 * 
 * @author Zlatko
 *
 */
@Entity
public class Dogadaj {
	/** Identifikator događaja */
	@Id
	@GeneratedValue
	private long id;
	/** Tekst događaja */
	@Column
	private String poruka;
	/** Vrijeme događaja */
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date vrijeme;

	/**
	 * Konstruktor.
	 * 
	 * @param poruka
	 *            poruka događaja
	 */
	public Dogadaj(String poruka) {
		this.poruka = poruka;
		this.vrijeme = new Date();
	}

	/**
	 * Konstruktor.
	 * 
	 */
	public Dogadaj() {
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
	 * @return poruka
	 */
	public String getPoruka() {
		return poruka;
	}

	/**
	 * Setter.
	 * 
	 * @param poruka
	 *            poruka
	 */
	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}

	/**
	 * Getter.
	 * 
	 * @return vrijeme
	 */
	public Date getVrijeme() {
		return vrijeme;
	}

	/**
	 * Setter.
	 * 
	 * @param vrijeme
	 *            vrijeme
	 */
	public void setVrijeme(Date vrijeme) {
		this.vrijeme = vrijeme;
	}

}
