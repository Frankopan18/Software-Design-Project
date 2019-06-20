package hr.finalium.ednevnik.model.roles;

import javax.persistence.Entity;

/**
 * Model podataka administratora.
 * 
 * @author Zlatko
 *
 */
@Entity
public class Administrator extends Korisnik {

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Administrator)) {
			return false;
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
