package hr.finalium.ednevnik.dao;

import hr.finalium.ednevnik.dao.jpa.JPADAOImpl;

/**
 * Singleton razred koji čuva implementaciju DAO razreda koja se koristi.
 * 
 * @author Zlatko
 *
 */
public class DAOProvider {

	/** Implementacija DAO objekta koja se koristi. */
	private static DAO dao = new JPADAOImpl();

	/**
	 * Getter.
	 * 
	 * @return DAO objekt.
	 */
	public static DAO getDAO() {
		return dao;
	}

}