package eu.additude.demo.rest;

import eu.additude.demo.controller.PersoonService;
import eu.additude.demo.dto.PersoonDTO;
import eu.additude.demo.model.Persoon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PersoonEndpoint {
    @Autowired
    PersoonService service; //= new PersoonService();

    @GetMapping("personen/{id}")  // org.springframework
//    @ResponseBody // doordat het een RestController is, hoeft dit niet. Spring doet dit bij default!! => mag weg
    //The @ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON and passed back into the HttpResponse object
    public ResponseEntity<PersoonDTO> getPersoonById(@PathVariable Long id) {
        System.out.println("LOG- REST: personen/" + id + " - Aanroep van onze restserivce voor het opvragen van één persoon.");
        Optional<Persoon> persoonOptional = service.findPersoonById(id);


//        return persoonOptional // Dit is voor over een aantal weken.
//                .map(persoon -> new ResponseEntity<>(new PersoonDTO(persoon), HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        if (persoonOptional.isPresent()) {
            Persoon persoon = persoonOptional.get();
            return new ResponseEntity<>(new PersoonDTO(persoon), HttpStatus.OK);
        } else {
            // Als er geen persoon gevonden is, willen we geen OK(200) teruggeven, maar iets anders.
            // Dat is de reden dat deze methode een ResponseEntity teruggeeft en niet alleen de inhoud/body/PersoonDTO
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("personen")
    public List<PersoonDTO> getAllePersonen() {
        System.out.println("LOG- REST: personen - Aanroep van onze restserivce voor het opvragen van één persoon.");

//        return service.getAllePersonen()
//                .stream()
//                .map(persoon -> new PersoonDTO(persoon))  // zet een persoon om in een PersoonDTO
//                .collect(Collectors.toList()); // alles op de band weer verzamelen in een List.

        List<Persoon> personen = service.getAllePersonen();
        List<PersoonDTO> dtoPersonen = personen
                .stream() // maak er een lopende band van
                .map(persoon -> new PersoonDTO(persoon))  // zet een persoon om in een PersoonDTO
                .collect(Collectors.toList()); // alles op de band weer verzamelen in een List.
        return dtoPersonen; // Manier 2 sturen we nu terug. bij manier 1 krijgen we door a & b een dubbele lijst
    }

    @PostMapping("personen")  // Post zorgt ervoor dat de mapping gelijk kan blijven. cool
    @ResponseStatus(HttpStatus.CREATED)
    public Persoon postPersoon(@RequestBody Persoon persoon) {  // Dit is de persoon die we in het bericht binnenkrijgen
        System.out.println("LOG- REST: personen/add - Aanroep van onze restserivce voor het toevoegen van één persoon.");
        return service.postPersoon(persoon); // Eigenlijk moeten we de persoon die binnekomst, qua gegevens overzetten in een nieuw persoon
    }



    ////////////////// Oude manier (via nep db) ////////////////////
    @GetMapping("personenIntern/{id}")  // org.springframework
//    @ResponseBody // doordat het een RestController is, hoeft dit niet. Spring doet dit bij default!! => mag weg
    //The @ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON and passed back into the HttpResponse object
    public ResponseEntity<PersoonDTO> getPersoonByIdIntern(@PathVariable Long id) {
        System.out.println("LOG- REST: personen/" + id + " - Aanroep van onze restserivce voor het opvragen van één persoon.");
        Persoon persoon = service.findPersoonByIdIntern(id);

        if (persoon != null) {
            return new ResponseEntity<>(new PersoonDTO(persoon), HttpStatus.OK);
        } else {
            // Als er geen persoon gevonden is, willen we geen OK(200) teruggeven, maar iets anders.
            // Dat is de reden dat deze methode een ResponseEntity teruggeeft en niet alleen de inhoud/body/PersoonDTO
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("personenIntern")
//    @ResponseBody // De Spring magie maakt er dan een Http bericht van met in de body de lijst van personen in JSON vorm
    public List<PersoonDTO> getAllePersonenIntern() {
        System.out.println("LOG- REST: personen - Aanroep van onze restserivce voor het opvragen van één persoon.");
        List<Persoon> personen = service.getAllePersonenIntern();

        // Manier 1 om van een personelijst naar een dto lijst te gaan
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
