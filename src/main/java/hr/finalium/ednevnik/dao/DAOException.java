package hr.finalium.ednevnik.dao;

/**
 * Iznimka koja se baca kada se dogodi pogreška kod dohvata/spremanja podataka
 * korištenjem DAO objekta.
 * 
 * @author Zlatko
 *
 */
public class DAOException extends RuntimeException {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor.
	 * 
	 * @param message
	 *            poruka s opisom greške.
	 * @param cause
	 *            razlog greške.
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Konstruktor.
	 * 
	 * @param message
	 *            poruka s opisom greške.
	 */
	public DAOException(String message) {
		super(message);
	}
}