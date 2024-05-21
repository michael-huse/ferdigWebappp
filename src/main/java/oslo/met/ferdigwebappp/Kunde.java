package oslo.met.ferdigwebappp;

public class Kunde {
    private int KundeNr;
    private String navn;
    private String adresse;
    private String passord;

    /*
     * Parameterized constructor
     * Allows for the creation of a Bilett object with all fields set.
     * Useful for testing and manual object creation in code.
     */

    public Kunde(int KundeNr, String navn, String adresse, String passord) {
        this.KundeNr = KundeNr;
        this.navn = navn;
        this.adresse = adresse;
        this.passord = passord;
    }

    /*
     * No-argument constructor
     * Required by frameworks like Spring and libraries like JPA/Hibernate
     * for object creation and deserialization of JSON data into Java objects.
     */

    public Kunde() {
    }

    public int getKundeNr() {
        return KundeNr;
    }

    public void setKundeNr(int KundeNr) {
        this.KundeNr = KundeNr;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }
}
