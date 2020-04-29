package eu.additude.demo.dto;

import eu.additude.demo.model.Persoon;

public class PersoonDTO {  //DTO Data Transfer Object
    private String naam;
    private String telefoonnummer;

    public PersoonDTO(Persoon persoon) {
        setNaam(maakSamenGesteldeNaam(persoon));
        setTelefoonnummer(persoon.getTelefoonnummer());
    }

    private String maakSamenGesteldeNaam(Persoon persoon) {
        return (persoon.getVoornaam() + " " + persoon.getTussenvoegsel()).trim() + " " + persoon.getAchternaam();
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }
}