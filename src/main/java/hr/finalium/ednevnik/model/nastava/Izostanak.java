/**
 * 
 */
package hr.finalium.ednevnik.model.nastava;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hr.finalium.ednevnik.model.roles.Ucenik;

/**
 * Model izostanka.
 * 
 * @author Zlatko
 *
 */
@Entity
public class Izostanak {
	/** ID izostanka */
	@Id
	@GeneratedValue
	private long id;
	/** Predmet sa kojeg se izostalo */
	@OneToOne
	private Predmet predmet;
	/** Stanje izostanka */
	@Enumerated
	private Stanje stanje;
	/** Učenik koji je izostao */
	@OneToOne
	private Ucenik ucenik;
	/** Komentar profesora koji je unio izostanak */
	@Column(length = 50)
	private String komentarProfesora;
	/** Komentar razrednika koji je/nije opravdao izostanak */
	@Column(length = 50)
	private String komentarRazrednika;
	/** Vrijeme upisa izostanka */
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date vrijeme;

	/**
	 * Konstruktor.
	 *
	 */
	public Izostanak() {
	}

	/**
	 * Konstruktor.
	 * 
	 * @param komentarProfesora
	 *            komentar profesora
	 */
	public Izostanak(String komentarProfesora) {
		this.stanje = Stanje.NA_CEKANJU;
		this.vrijeme = new Date();
		this.komentarProfesora = komentarProfesora;
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

	/**
	 * Getter.
	 * 
	 * @return stanje
	 */
	public Stanje getStanje() {
		return stanje;
	}

	/**
	 * Setter.
	 * 
	 * @param stanje
	 *            stanje
	 */
	public void setStanje(Stanje stanje) {
		this.stanje = stanje;
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
	 * @return komentarProfesora
	 */
	public String getKomentarProfesora() {
		return komentarProfesora;
	}

	/**
	 * Setter.
	 * 
	 * @param komentarProfesora
	 *            komentarProfesora
	 */
	public void setKomentarProfesora(String komentarProfesora) {
		this.komentarProfesora = komentarProfesora;
	}

	/**
	 * Getter.
	 * 
	 * @return komentarRazrednika
	 */
	public String getKomentarRazrednika() {
		return komentarRazrednika;
	}

	/**
	 * Setter.
	 * 
	 * @param komentarRazrednika
	 *            komentarRazrednika
	 */
	public void setKomentarRazrednika(String komentarRazrednika) {
		this.komentarRazrednika = komentarRazrednika;
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

	/**
	 * Stanje izostanka
	 * 
	 * @author Zlatko
	 *
	 */
	public enum Stanje {
		/** Opravdan izostanak */
		OPRAVDAN,
		/** Neopravdan izostanak */
		NEOPRAVDAN,
		/** Izostanak kojem stanje još nije dodijeljeno */
		NA_CEKANJU
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
		if (!(obj instanceof Izostanak)) {
			return false;
		}
		Izostanak other = (Izostanak) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return predmet + " " + ucenik + " " + komentarProfesora + " " + vrijeme;
	}
}
