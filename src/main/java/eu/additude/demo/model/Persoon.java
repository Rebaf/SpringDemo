package eu.additude.demo.model;

import javax.persistence.*;

@Entity // javax.persistence
//@SequenceGenerator(name="seq", initialValue=10)
public class Persoon {
    @Id // javax.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Moet een object zijn vanwege CrudRepository
    @Column(unique = true) // heeft altijd betrekking op het/de eerstvolgende field/instantie variabele
    private String bsn;
    @Column(name = "voornaam") // Zonder deze oplossing, zouden we in het insert statement als veld voor_naam moeten gebruiken. // Hoofdletters worden omgezet naar _letter
    private String voorNaam; // Zonder de mapping van de @Column zou dit veld in de db voor_naam zijn.
    private String tussenvoegsel;
//    @Column(name = "hoeheetjenouookalweer") // Wel de data.sql of import.sql hierop aanpassen.
    private String achternaam;
    private String telefoonnummer;

    private Persoon() {} // Zonder deze (private???) constructor gaat het mis. Spring/CrudRepository trekt zich dus NIETS van private aan...

    public Persoon(Long id, String bsn, String voorNaam, String tussenvoegsel, String achternaam, String telefoonnummer) {
        setId(id);
        setBsn(bsn);
        setVoorNaam(voorNaam);
        setTussenvoegsel(tussenvoegsel);
        setAchternaam(achternaam);
        setTelefoonnummer(telefoonnummer);
    }

    public Persoon(Persoon other) {
        setBsn(other.getBsn()); // bsn check (en andere validatie methodes v/d gegevens)
        setVoorNaam(other.getVoorNaam());
        setTussenvoegsel(other.getTussenvoegsel());
        setAchternaam(other.getAchternaam());
        setTelefoonnummer(other.getTelefoonnummer()); // verkeerd telefoonnummer etc
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public String getVoorNaam() {
        return voorNaam;
    }

    public void setVoorNaam(String voorNaam) {
        this.voorNaam = voorNaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }
}