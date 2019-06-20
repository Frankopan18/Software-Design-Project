/**
 * 
 */
package hr.finalium.ednevnik.model.view;

/**
 * @author Zlatko
 *
 */
public class RazredViewModel {
	/** ID razreda */
	private long id;
	/** Oznaka razreda */
	private String oznaka;
	/** Poruka koja se ispisuje kada je oznaka razreda nelegalna */
	private String oznakaGreska;
	/** Školska godina */
	private String godina;
	/** Poruka koja se ispisuje kada je godina nelegalna */
	private String godinaGreska;

	/**
	 * Konstruktor.
	 * 
	 * @param id
	 *            ID razreda
	 * @param oznaka
	 *            oznaka razreda
	 * @param godina
	 *            školska godina
	 */
	public RazredViewModel(long id, String oznaka, String godina) {
		this.id = id;
		this.oznaka = oznaka;
		if (oznaka == null || oznaka.trim().length() == 0) {
			oznakaGreska = "Oznaka ne može biti prazna";
		}
		this.godina = godina;
		if (godina == null || !godina.matches("[0-9]+")) {
			godinaGreska = "Godina mora biti broj";
		}
	}

	/**
	 * Konstruktor.
	 * 
	 */
	public RazredViewModel() {
	}

	/**
	 * Vraća true ako su podaci u modelu valjani
	 * 
	 * @return true ako su podaci u modelu valjani, inače false
	 */
	public boolean podaciKonzistentni() {
		return oznakaGreska == null && godinaGreska == null;
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
	 * @return oznaka
	 */
	public String getOznaka() {
		return oznaka;
	}

	/**
	 * Setter.
	 * 
	 * @param oznaka
	 *            oznaka
	 */
	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	/**
	 * Getter.
	 * 
	 * @return oznakaGreska
	 */
	public String getOznakaGreska() {
		return oznakaGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param oznakaGreska
	 *            oznakaGreska
	 */
	public void setOznakaGreska(String oznakaGreska) {
		this.oznakaGreska = oznakaGreska;
	}

	/**
	 * Getter.
	 * 
	 * @return godina
	 */
	public String getGodina() {
		return godina;
	}

	/**
	 * Setter.
	 * 
	 * @param godina
	 *            godina
	 */
	public void setGodina(String godina) {
		this.godina = godina;
	}

	/**
	 * Getter.
	 * 
	 * @return godinaGreska
	 */
	public String getGodinaGreska() {
		return godinaGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param godinaGreska
	 *            godinaGreska
	 */
	public void setGodinaGreska(String godinaGreska) {
		this.godinaGreska = godinaGreska;
	}

}
