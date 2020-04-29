package eu.additude.demo.model;

import eu.additude.demo.model.validations.Age;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity // javax.persistence
//@SequenceGenerator(name="seq", initialValue=10)
public class Persoon {

    @Id // javax.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Moet een object zijn vanwege CrudRepository

    @Column(unique = true) // heeft altijd betrekking op het/de eerstvolgende field/instantie variabele
    // 9.999.999
    @Pattern(regexp = "^[0-9].[0-9][0-9][0-9].[0-9][0-9][0-9]$", message = "BSN is niet juiste formaat")
    private String bsn;

    @Column(name = "voornaam") // Zonder deze oplossing, zouden we in het insert statement als veld voor_naam moeten gebruiken. // Hoofdletters worden omgezet naar _letter
    private String voornaam; // In geval van voorNaam zorgt de mapping van de @Column zou dit veld in de db voor_naam zijn.

    @NotNull
    private String tussenvoegsel;

    @NotEmpty(message = "Achternaam is verplicht")
    private String achternaam;

    private String telefoonnummer;

    @Age(message = "De leeftijd moet tussen {min} en {max} liggen", min = 18, max = 35)
    private Integer leeftijd;

    private Persoon() {} // Zonder deze (private???) constructor gaat het mis. Spring/CrudRepository trekt zich dus NIETS van private aan...

    public Persoon(Long id, String bsn, String voorNaam, String tussenvoegsel, String achternaam, String telefoonnummer) {
        setId(id);
        setBsn(bsn);
        setVoornaam(voorNaam);
        setTussenvoegsel(tussenvoegsel);
        setAchternaam(achternaam);
        setTelefoonnummer(telefoonnummer);
    }

    public Persoon(Persoon other) {
        setBsn(other.getBsn()); // bsn check (en andere validatie methodes v/d gegevens)
        setVoornaam(other.getVoornaam());
        setTussenvoegsel(other.getTussenvoegsel());
        setAchternaam(other.getAchternaam());
        setTelefoonnummer(other.getTelefoonnummer()); // verkeerd telefoonnummer etc
    }

    public Integer getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(Integer leeftijd) {
        this.leeftijd = leeftijd;
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

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
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