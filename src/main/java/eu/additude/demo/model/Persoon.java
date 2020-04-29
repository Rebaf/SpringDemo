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

    private String voornaam; // Zonder de mapping van de @Column zou dit veld in de db voor_naam zijn.

    @NotNull
    private String tussenvoegsel;

    @NotEmpty(message = "Achternaam is verplicht")
    private String achternaam;

    private String telefoonnummer;

    @Age(message = "De leeftijd moet tussen {min} en {max} liggen", min = 18, max = 35)
    private Integer leeftijd;

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