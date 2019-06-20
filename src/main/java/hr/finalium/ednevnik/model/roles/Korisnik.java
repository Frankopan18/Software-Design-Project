package hr.finalium.ednevnik.model.roles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Model korisnika koji se prijavljuje u sustav.
 * 
 * @author Janko
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Korisnik {
	/** E-mail korisnika */
	@Id
	@Column(length = 50, nullable = false, unique = true)
	private String mail;
	/** Hash vrijednost lozinke */
	@Column(nullable = false)
	private int lozinka;

	/**
	 * Konstruktor.
	 */
	public Korisnik() {
	}

	/**
	 * Konstruktor.
	 * 
	 * @param mail
	 *            e-mail adresa korisnika
	 * @param hashLozinka
	 *            hash vrijednost lozinke korisnika
	 */
	public Korisnik(String mail, int hashLozinka) {
		this.mail = mail;
		this.lozinka = hashLozinka;
	}

	/**
	 * Getter
	 * 
	 * @return e-mail korisnika
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Setter
	 * 
	 * @param mail
	 *            e-mail korisnika
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Getter
	 * 
	 * @return lozinka korisnika
	 */
	public int getLozinka() {
		return lozinka;
	}

	/**
	 * Setter
	 * 
	 * @param lozinka
	 *            lozinka korisnika
	 */
	public void setLozinka(int lozinka) {
		this.lozinka = lozinka;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Korisnik)) {
			return false;
		}
		Korisnik other = (Korisnik) obj;
		return this.mail.equals(other.mail);
	}

	@Override
	public int hashCode() {
		return mail.hashCode();
	}
}
