package eu.additude.demo.dto;

import eu.additude.demo.model.Persoon;
import eu.additude.guides.gs_producing_web_service.PersoonSoap;

public class PersoonDTO {  //DTO Data Transfer Object
    private Long id;
    private String naam;
    private String telefoonnummer;
    private Long afdeling;
    private String afdelingNaam;

    public PersoonDTO(Persoon persoon) {
        setId(persoon.getId());
        setNaam(maakSamenGesteldeNaam(persoon));
        setTelefoonnummer(persoon.getTelefoonnummer());
        if (persoon.getAfdeling() != null) {
            setAfdeling(persoon.getAfdeling().getId());
            setAfdelingNaam(persoon.getAfdeling().getNaam());
        }
    }

    public static PersoonSoap createPersoonSOAP(Persoon persoon) {
        PersoonSoap soap = new PersoonSoap();
        soap.setId(persoon.getId());
        soap.setNaam(maakSamenGesteldeNaam(persoon));
        soap.setTelefoonnummer(persoon.getTelefoonnummer());
        if (persoon.getAfdeling() != null) {
            soap.setAfdeling(persoon.getAfdeling().getId());
            soap.setAfdelingNaam(persoon.getAfdeling().getNaam());
        }
        return soap;
    }

    private static String maakSamenGesteldeNaam(Persoon persoon) {
        return (persoon.getVoornaam() + " " + persoon.getTussenvoegsel()).trim() + " " + persoon.getAchternaam();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getAfdeling() {
        return afdeling;
    }

    public void setAfdeling(Long afdeling) {
        this.afdeling = afdeling;
    }

    public String getAfdelingNaam() {
        return afdelingNaam;
    }

    public void setAfdelingNaam(String afdelingNaam) {
        this.afdelingNaam = afdelingNaam;
    }
}