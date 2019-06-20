package hr.finalium.ednevnik.model.roles;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

/**
 * Model podataka roditelja.
 * 
 * @author Janko
 *
 */
@Entity
public class Roditelj extends Korisnik {
	/** Ime roditelja */
	@Column(nullable = false)
	private String ime;
	/** Prezime roditelja */
	@Column(nullable = false)
	private String prezime;
	/** Roditeljeva djeca */
	@ManyToMany(mappedBy = "roditelji", fetch = FetchType.EAGER)
	private Set<Ucenik> djeca = new HashSet<>();
	/**Broj telefona roditelja*/
	@Column(nullable = false)
	private String brojTelefona;

	/**
	 * Konstruktor.
	 * 
	 * @param ime
	 *            ime roditelja
	 * @param prezime
	 *            prezime roditelja
	 * @param brojTelefona broj telefona roditelja
	 * @param mail
	 *            e-mail roditelja
	 * @param lozinka
	 *            lozinka roditelja
	 */
	public Roditelj(String ime, String prezime, String brojTelefona, String mail, int lozinka) {
		super(mail, lozinka);
		this.brojTelefona = brojTelefona;
		this.ime = ime;
		this.prezime = prezime;
	}

	/**
	 * Konstruktor.
	 *
	 */
	public Roditelj() {

	}

	/**
	 * Getter.
	 * 
	 * @return ime roditelja
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * Setter.
	 * 
	 * @param ime
	 *            ime roditelja
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}

	/**
	 * Getter.
	 * 
	 * @return prezime roditelja
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * Setter.
	 * 
	 * @param prezime
	 *            prezime roditelja
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	/**
	 * Getter.
	 * 
	 * @return djeca
	 */
	public Set<Ucenik> getDjeca() {
		return djeca;
	}

	/**
	 * Setter.
	 * 
	 * @param djeca
	 *            djeca
	 */
	public void setDjeca(Set<Ucenik> djeca) {
		this.djeca = djeca;
	}

	/**
	 * Getter.
	 * 
	 * @return brojTelefona
	 */
	public String getBrojTelefona() {
		return brojTelefona;
	}

	/**
	 * Setter.
	 * 
	 * @param brojTelefona
	 *            brojTelefona
	 */
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Roditelj)) {
			return false;
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
