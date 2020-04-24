package eu.additude.demo.rest;

import eu.additude.demo.controller.PersoonNoDbService;
import eu.additude.demo.dto.PersoonDTO;
import eu.additude.demo.model.Persoon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersoonNoDbEndpoint {
    @Autowired
    PersoonNoDbService service; //= new PersoonService();
    ////////////////// Oude manier (via nep db) ////////////////////
    @GetMapping("personenNoDb/{id}")  // org.springframework
//    @ResponseBody // doordat het een RestController is, hoeft dit niet. Spring doet dit bij default!! => mag weg
    //The @ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON and passed back into the HttpResponse object
    public ResponseEntity<PersoonDTO> getPersoonByIdNoDb(@PathVariable Long id) {
        System.out.println("LOG- REST: personenNoDb/" + id + " - Aanroep van onze restserivce voor het opvragen van één persoon.");
        Persoon persoon = service.findPersoonByIdNoDb(id);

        if (persoon != null) {
            return new ResponseEntity<>(new PersoonDTO(persoon), HttpStatus.OK);
        } else {
            // Als er geen persoon gevonden is, willen we geen OK(200) teruggeven, maar iets anders.
            // Dat is de reden dat deze methode een ResponseEntity teruggeeft en niet alleen de inhoud/body/PersoonDTO
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("personenNoDb")
//    @ResponseBody // De Spring magie maakt er dan een Http bericht van met in de body de lijst van personen in JSON vorm
    public List<PersoonDTO> getAllePersonenNoDb() {
        System.out.println("LOG- REST: personenNoDb - Aanroep van onze restserivce voor het opvragen van één persoon.");
        List<Persoon> personen = service.getAllePersonenNoDb();

        // Manier 1 om van een personenlijst naar een dto lijst te gaan
        List<PersoonDTO> dtoLijst1 = new ArrayList<>();
        // 1 a) met for each (lambda/streams manier)
        personen.forEach(persoon -> dtoLijst1.add(new PersoonDTO(persoon)));
        // 1 b) of met enhanced for loop
        for (Persoon persoon1 : personen) {
            dtoLijst1.add(new PersoonDTO(persoon1));
        }

        // Manier 2: streamen, mappen en collecten
        List<PersoonDTO> dtoLijst2 = personen
                .stream() // maak er een lopende band van
                .map(persoon -> new PersoonDTO(persoon))  // zet een persoon om in een PersoonDTO
                .collect(Collectors.toList()); // alles op de band weer verzamelen in een List.
        return dtoLijst2; // Manier 2 sturen we nu terug. bij manier 1 krijgen we door a & b een dubbele lijst
    }
}
