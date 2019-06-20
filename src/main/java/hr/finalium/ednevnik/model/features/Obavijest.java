package hr.finalium.ednevnik.model.features;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Model podataka obavijesti.
 * 
 * @author Janko
 *
 */
@Entity
public class Obavijest {

	/** Id obavijesti */
	@Id
	@GeneratedValue
	private long id;
	/** Naslov obavijesti */
	@Column(length = 50, nullable = false)
	private String naslov;
	/** Tekst obavijesti */
	@Column(length = 1000, nullable = false)
	private String tekst;
	/** Datum objave obavijesti */
	@Column(nullable = false)
	private Date datumStvaranja;
	/** Slika */
	@Lob
	@Column(length=10_000_000)
	private byte[] slika;
	/** Zastavica aktivnosti obavijesti */
	@Column
	private boolean aktivna;

	/**
	 * Konstruktor.
	 * 
	 * @param naslov
	 *            naslov obavijesti
	 * @param tekst
	 *            tekst obavijesti
	 * @param datum
	 *            datum stvaranja obavijesti
	 * @param slika
	 *            slika
	 */
	public Obavijest(String naslov, String tekst, Date datum, byte[] slika) {
		super();
		this.naslov = naslov;
		this.tekst = tekst;
		this.datumStvaranja = datum;
		this.slika = slika;
		aktivna = true;
	}

	/**
	 * Konstruktor.
	 */
	public Obavijest() {
	}

	/**
	 * Getter
	 * 
	 * @return id obavijesti
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter
	 * 
	 * @param id
	 *            obavijesti
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Getter
	 * 
	 * @return tekst obavijesti
	 */
	public String getTekst() {
		return tekst;
	}

	/**
	 * Setter
	 * 
	 * @param tekst
	 *            obavijesti
	 */
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	/**
	 * Getter
	 * 
	 * @return naslov obavijesti
	 */
	public String getNaslov() {
		return naslov;
	}

	/**
	 * Setter
	 * 
	 * @param naslov
	 *            obavijesti
	 */
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	/**
	 * Getter
	 * 
	 * @return datum objave obavijesti
	 */
	public Date getDatumStvaranja() {
		return datumStvaranja;
	}

	/**
	 * Setter
	 * 
	 * @param datum
	 *            objave obavijesti
	 */
	public void setDatumStvaranja(Date datum) {
		this.datumStvaranja = datum;
	}

	/**
	 * Getter
	 * 
	 * @return slika obavijesti
	 */
	public byte[] getSlika() {
		return slika;
	}

	/**
	 * Setter
	 * 
	 * @param slika
	 *            obavijesti
	 */
	public void setSlika(byte[] slika) {
		this.slika = slika;
	}

	/**
	 * Getter
	 * 
	 * @return zastavica aktivnosti obavijesti
	 */
	public boolean isAktivna() {
		return aktivna;
	}

	/**
	 * Setter
	 * 
	 * @param aktivna
	 *            aktivnosti obavijesti
	 */
	public void setAktivna(boolean aktivna) {
		this.aktivna = aktivna;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Obavijest)) {
			return false;
		}
		Obavijest other = (Obavijest) obj;
		return this.id == other.id;
	}

	@Override
	public int hashCode() {
		return Long.hashCode(id);
	}
}
