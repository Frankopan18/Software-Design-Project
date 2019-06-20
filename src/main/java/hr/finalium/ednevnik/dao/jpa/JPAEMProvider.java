package hr.finalium.ednevnik.dao.jpa;

import javax.persistence.EntityManager;

import hr.finalium.ednevnik.dao.DAOException;

/**
 * Razred koji stvara i zatvara {@link EntityManager} za pojedinu dretvu.
 * 
 * @author Zlatko
 *
 */
public class JPAEMProvider {
	/** Mapa koja za svaku dretvu sprema njen entity manager. */
	private static ThreadLocal<LocalData> locals = new ThreadLocal<>();

	/**
	 * Getter.
	 * 
	 * @return entity manager za trenutnu dretvu.
	 */
	public static EntityManager getEntityManager() {
		LocalData ldata = locals.get();
		if (ldata == null) {
			ldata = new LocalData();
			ldata.em = JPAEMFProvider.getEmf().createEntityManager();
			ldata.em.getTransaction().begin();
			locals.set(ldata);
		}
		return ldata.em;
	}

	/**
	 * Zatvara entity manager pojedine dretve.
	 * 
	 * @throws DAOException
	 *             ako se dogodi greška kod zatvaranja EM-a.
	 */
	public static void close() throws DAOException {
		LocalData ldata = locals.get();
		if (ldata == null) {
			return;
		}
		DAOException dex = null;
		try {
			ldata.em.getTransaction().commit();
		} catch (Exception ex) {
			dex = new DAOException("Unable to commit transaction.", ex);
		}
		try {
			ldata.em.close();
		} catch (Exception ex) {
			if (dex != null) {
				dex = new DAOException("Unable to close entity manager.", ex);
			}
		}
		locals.remove();
		if (dex != null)
			throw dex;
	}

	/**
	 * Razred koji se sprema u {@link ThreadLocal}, sadrži entity manager.
	 * 
	 * @author Zlatko
	 *
	 */
	private static class LocalData {
		/** Entity manager */
		EntityManager em;
	}

}