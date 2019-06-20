package hr.finalium.ednevnik.dao.jpa;

import javax.persistence.EntityManagerFactory;

/**
 * Singleton razred koji sadrži {@link EntityManagerFactory} koji se koristi u
 * JPA implementaciji DAO objekta.
 * 
 * @author Zlatko
 *
 */
public class JPAEMFProvider {
	/** Entity Manager Factory. */
	public static EntityManagerFactory emf;

	/**
	 * Getter.
	 * 
	 * @return entity manager factory.
	 */
	public static EntityManagerFactory getEmf() {
		return emf;
	}

	/**
	 * Setter.
	 * 
	 * @param emf
	 *            entity manager factory.
	 */
	public static void setEmf(EntityManagerFactory emf) {
		JPAEMFProvider.emf = emf;
	}
}