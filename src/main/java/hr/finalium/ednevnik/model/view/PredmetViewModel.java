/**
 * 
 */
package hr.finalium.ednevnik.model.view;

import hr.finalium.ednevnik.model.nastava.Predmet;

/**
 * @author Zlatko
 *
 */
public class PredmetViewModel {
	/** ID predmeta */
	private long id;
	/** Naziv predmeta */
	private String naziv;
	/** Poruka koja se ispisuje kada naziv predmeta nije legalan */
	private String nazivGreska;
	/** Školska godina u kojoj se predmet predaje */
	private String skolskaGodina;
	/** Poruka koja se ispisuje kada školska godina nije legalna */
	private String skolskaGodinaGreska;
	/** Opis predmeta */
	private String opis;
	/** Poruka koja se ispisuje kada opis nije legalan */
	private String opisGreska;
	/** Nastavni plan predmeta */
	private byte[] nastavniPlan;
	/** Nastavno pismo predmeta */
	private byte[] nastavnoPismo;
	/** Kategorije ocjena */
	private String kategorije;
	/** Poruka koja se ispisuje kada kategorije nisu valjane */
	private String kategorijeGreska;

	/**
	 * Konstruktor.
	 * 
	 * @param id
	 *            ID predmeta
	 * @param naziv
	 *            naziv predmeta
	 * @param skolskaGodina
	 *            školska godina u kojoj se predmet predaje
	 * @param opis
	 *            opis predmeta
	 * @param nastavniPlan
	 *            nastavni plan
	 * @param nastavnoPismo
	 *            nastavno pismo
	 * @param kategorije
	 *            kategorije ocjena
	 */
	public PredmetViewModel(long id, String naziv, String skolskaGodina, String opis, byte[] nastavniPlan,
			byte[] nastavnoPismo, String kategorije) {
		super();
		this.id = id;
		this.naziv = naziv;
		if (naziv == null || naziv.trim().length() == 0) {
			nazivGreska = "Naziv ne smije biti prazan";
		}
		this.skolskaGodina = skolskaGodina;
		if (skolskaGodina == null || skolskaGodina.trim().length() == 0 || !skolskaGodina.matches("[0-9]+")) {
			skolskaGodinaGreska = "Školska godina mora biti broj";
		}
		this.opis = opis;
		if (opis == null || opis.trim().length() == 0) {
			opisGreska = "Opis ne smije biti prazan";
		}
		this.nastavniPlan = nastavniPlan;
		this.nastavnoPismo = nastavnoPismo;
		this.kategorije = kategorije;
		if (kategorije == null || kategorije.trim().length() == 0) {
			kategorijeGreska = "Morate dodati barem jednu kategoriju";
		}
	}

	/**
	 * Konstruktor.
	 * 
	 */
	public PredmetViewModel() {
	}

	/**
	 * Konstruktor.
	 * 
	 * @param predmet
	 *            predmet za kojeg se radi view model
	 */
	public PredmetViewModel(Predmet predmet) {
		this(predmet.getId(), predmet.getNaziv(), Integer.toString(predmet.getSkolskaGodina()), predmet.getOpis(),
				predmet.getNastavniPlan(), predmet.getNastavnoPismo(), "blank");
		StringBuilder k = new StringBuilder();
		predmet.getKategorije().forEach(kat -> k.append(k + ", "));
		kategorije = k.substring(k.length() - 2);
	}

	/**
	 * Provjerava jesu li uneseni podatci valjani.
	 * 
	 * @return true ako su podatci valjani, inače false
	 */
	public boolean podaciKonzistentni() {
		return nazivGreska == null && skolskaGodinaGreska == null && opisGreska == null;
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
	 * @return naziv
	 */
	public String getNaziv() {
		return naziv;
	}

	/**
	 * Setter.
	 * 
	 * @param naziv
	 *            naziv
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	/**
	 * Getter.
	 * 
	 * @return nazivGreska
	 */
	public String getNazivGreska() {
		return nazivGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param nazivGreska
	 *            nazivGreska
	 */
	public void setNazivGreska(String nazivGreska) {
		this.nazivGreska = nazivGreska;
	}

	/**
	 * Getter.
	 * 
	 * @return skolskaGodina
	 */
	public String getSkolskaGodina() {
		return skolskaGodina;
	}

	/**
	 * Setter.
	 * 
	 * @param skolskaGodina
	 *            skolskaGodina
	 */
	public void setSkolskaGodina(String skolskaGodina) {
		this.skolskaGodina = skolskaGodina;
	}

	/**
	 * Getter.
	 * 
	 * @return skolskaGodinaGreska
	 */
	public String getSkolskaGodinaGreska() {
		return skolskaGodinaGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param skolskaGodinaGreska
	 *            skolskaGodinaGreska
	 */
	public void setSkolskaGodinaGreska(String skolskaGodinaGreska) {
		this.skolskaGodinaGreska = skolskaGodinaGreska;
	}

	/**
	 * Getter.
	 * 
	 * @return opis
	 */
	public String getOpis() {
		return opis;
	}

	/**
	 * Setter.
	 * 
	 * @param opis
	 *            opis
	 */
	public void setOpis(String opis) {
		this.opis = opis;
	}

	/**
	 * Getter.
	 * 
	 * @return opisGreska
	 */
	public String getOpisGreska() {
		return opisGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param opisGreska
	 *            opisGreska
	 */
	public void setOpisGreska(String opisGreska) {
		this.opisGreska = opisGreska;
	}

	/**
	 * Getter.
	 * 
	 * @return nastavniPlan
	 */
	public byte[] getNastavniPlan() {
		return nastavniPlan;
	}

	/**
	 * Setter.
	 * 
	 * @param nastavniPlan
	 *            nastavniPlan
	 */
	public void setNastavniPlan(byte[] nastavniPlan) {
		this.nastavniPlan = nastavniPlan;
	}

	/**
	 * Getter.
	 * 
	 * @return nastavnoPismo
	 */
	public byte[] getNastavnoPismo() {
		return nastavnoPismo;
	}

	/**
	 * Setter.
	 * 
	 * @param nastavnoPismo
	 *            nastavnoPismo
	 */
	public void setNastavnoPismo(byte[] nastavnoPismo) {
		this.nastavnoPismo = nastavnoPismo;
	}

	/**
	 * Getter.
	 * 
	 * @return kategorije
	 */
	public String getKategorije() {
		return kategorije;
	}

	/**
	 * Setter.
	 * 
	 * @param kategorije
	 *            kategorije
	 */
	public void setKategorije(String kategorije) {
		this.kategorije = kategorije;
	}

	/**
	 * Getter.
	 * 
	 * @return kategorijeGreska
	 */
	public String getKategorijeGreska() {
		return kategorijeGreska;
	}

	/**
	 * Setter.
	 * 
	 * @param kategorijeGreska
	 *            kategorijeGreska
	 */
	public void setKategorijeGreska(String kategorijeGreska) {
		this.kategorijeGreska = kategorijeGreska;
	}
}
