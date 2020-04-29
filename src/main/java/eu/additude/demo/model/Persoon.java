package eu.additude.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
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

    //    @Age(message = "Leeftijd moet tussen 20 en 61 liggen", min = 20, max = 61)
    private Integer leeftijd;

}