package hr.finalium.ednevnik.web;

/**
 * Razred koji sadrži konstante koje se ispisuju kod raznih događaja u sustavu.
 * 
 * @author Zlatko
 *
 */
public class Konstante {
	/** Poruka korisniku kada pokuša pristupiti stranici za koju nema ovlasti */
	public static final String NEMA_OVLASTI = "Nemate ovlasti za pristup ovoj stranici.";
	/** Poruka korisniku da je poslao zahtjev za dohvat nepostojećeg entiteta */
	public static final String NE_POSTOJI = "Podatak s traženim svojstvima ne postoji.";
	/** Poruka korisniku da je unio krive podatke za prijavu. */
	public static final String NEUSPJELA_PRIJAVA = "Korisnik s tom kombinacijom maila i lozinke ne postoji";
	public static final String ZAKLJUCNA_OCJENA = "Zaključna ocjena";
	public static final String OCJENA_ZAKLJUCENA = "Ocjena je već zaključena";
	public static final String PODATCI_NEKONZISTENTNI = "Podatci koje ste pokušali unijeti nisu valjani.";
}
