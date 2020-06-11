package eu.additude.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import eu.additude.demo.model.validations.Age;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity // javax.persistence
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Persoon {

    @Id // javax.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Moet een object zijn vanwege CrudRepository

    @Column(unique = true) // heeft altijd betrekking op het/de eerstvolgende field/instantie variabele
    // 9.999.999
    @Pattern(regexp = "^[0-9].[0-9][0-9][0-9].[0-9][0-9][0-9]$", message = "BSN is niet juiste formaat")
    private String bsn;

    @Column(name = "voornaam")
    // Zonder deze oplossing, zouden we in het insert statement als veld voor_naam moeten gebruiken. // Hoofdletters worden omgezet naar _letter
    private String voornaam; // In geval van voorNaam zorgt de mapping van de @Column zou dit veld in de db voor_naam zijn.

    @NotNull
    private String tussenvoegsel;

    @NotEmpty(message = "Achternaam is verplicht")
    private String achternaam;

    private String telefoonnummer;

    @ManyToOne
    @JsonIgnore // wel in database opslaan, niet in JSON
    private Afdeling afdeling;

    @Transient // niet in database opslaan, wel in JSON
    @JsonProperty("afdeling") // naam van het originele veld => naam in JSON
    private Long afdelingId;

//    @Column(name = "afdeling_id") // naam van het veld in de database
//    private Long afdeling; // naam van het veld in Java/JSON
//    // of zo, dan is de @Column niet nodig
//    private Long afdelingId; // met afdelingId(in Java en JSON) wordt deze gekoppeld aan het veld afdeling_id in de db

    @Age(message = "De leeftijd moet tussen {min} en {max} liggen", min = 18, max = 35)
    private Integer leeftijd;

    public Persoon() {
    } // Zonder deze (private???) constructor gaat het mis. Spring/CrudRepository trekt zich dus NIETS van private aan...

    public Persoon(Persoon other) {
        zetGegevensOver(other);
    }

    public void zetGegevensOver(Persoon other) {
        setBsn(other.bsn); // bsn check (en andere validatie methodes v/d gegevens)
        setVoornaam(other.voornaam);
        setTussenvoegsel(other.tussenvoegsel);
        setAchternaam(other.achternaam);
        setTelefoonnummer(other.telefoonnummer); // verkeerd telefoonnummer etc
        setLeeftijd(other.leeftijd);
        setAfdeling(other.afdeling);
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

    public Afdeling getAfdeling() {
        return afdeling;
    }

    public void setAfdeling(Afdeling afdeling) {
        this.afdeling = afdeling;
    }

    public Long getAfdelingId() {
        return afdelingId;
    }

    public void setAfdelingId(Long afdelingId) {
        this.afdelingId = afdelingId;
    }
}