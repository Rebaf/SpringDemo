package eu.additude.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // javax.persistence
public class Persoon {
    @Id // javax.persistence
    private Long id; // Moet een object zijn vanwege CrudRepository
    private String bsn;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private String telefoonnummer;

    private Persoon() {} // Zonder deze (private???) constructor gaat het mis. Spring/CrudRepository trekt zich dus NIETS van private aan...

    public Persoon(Long id, String bsn, String voornaam, String tussenvoegsel, String achternaam, String telefoonnummer) {
        setId(id);
        setBsn(bsn);
        setVoornaam(voornaam);
        setTussenvoegsel(tussenvoegsel);
        setAchternaam(achternaam);
        setTelefoonnummer(telefoonnummer);
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