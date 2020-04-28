package eu.additude.demo.rest;

import eu.additude.demo.controller.PersoonService;
import eu.additude.demo.dto.PersoonDTO;
import eu.additude.demo.model.Persoon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PersoonEndpoint {
    @Autowired
    PersoonService service;

    @GetMapping("personen/{id}")
    public ResponseEntity<PersoonDTO> getPersoonById(@PathVariable Long id) {
        System.out.println("LOG- GET: personen/" + id + " - Aanroep van onze restserivce voor het opvragen van één persoon.");
        Optional<Persoon> persoonOptional = service.findPersoonById(id);

        return persoonOptional // Dit is voor over een aantal weken.
                .map(persoon -> maakResponseEntityOk(persoon))
                .orElseGet(() -> maakResponseEntityNotFound());

//        return persoonOptional // Dit is voor over een aantal weken. Lambda omgezet naar een method reference
//                .map(this::maakResponseEntityOk)
//                .orElseGet(this::maakResponseEntityNotFound);

//        if (persoonOptional.isPresent()) {  // Zo is het eigenlijk een ouderwetse NullPointer check. Via een .map is aan te bevelen.
//            Persoon persoon = persoonOptional.get();
//            return new ResponseEntity<>(new PersoonDTO(persoon), HttpStatus.OK);
//        } else {
//            // Als er geen persoon gevonden is, willen we geen OK(200) teruggeven, maar iets anders.
//            // Dat is de reden dat deze methode een ResponseEntity teruggeeft en niet alleen de inhoud/body/PersoonDTO
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
    }

    private ResponseEntity<PersoonDTO> maakResponseEntityOk(Persoon persoon) {
        return new ResponseEntity<>(new PersoonDTO(persoon), HttpStatus.OK);
    }

    private ResponseEntity<PersoonDTO> maakResponseEntityNotFound() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("personen")
    public List<PersoonDTO> getAllePersonen() {
        System.out.println("LOG- GET: personen - Aanroep van onze restserivce voor het opvragen van één persoon.");

//        return service.getAllePersonen()
//                .stream()
//                .map(PersoonDTO::new)                     // zet een persoon om in een PersoonDTO
//                .collect(Collectors.toList());            // alles op de band weer verzamelen in een List.

        List<Persoon> personen = service.getAllePersonen();
        List<PersoonDTO> dtoPersonen = personen
                .stream()                                   // maak er een lopende band van
                .map(persoon -> new PersoonDTO(persoon))    // zet een persoon om in een PersoonDTO  // PersoonDTO::new // Korter en mooier
                .collect(Collectors.toList());              // alles op de band weer verzamelen in een List.
        return dtoPersonen;                                 // Manier 2 sturen we nu terug. bij manier 1 krijgen we door a & b nu natuurlijk alle personen dubbel
    }

    @PostMapping("personen")  // Post zorgt ervoor dat de mapping gelijk kan blijven, geen conflict met de GET voor alle personen. COOL.
    @ResponseStatus(HttpStatus.CREATED)
    public Persoon postPersoon(@RequestBody Persoon persoon) {      // Dit is de persoon die we in het bericht binnenkrijgen
        System.out.println("LOG- POST: personen - Aanroep van onze restserivce voor het toevoegen van één persoon.");

        // Eigenlijk moeten we de persoon die binnenkomt, qua gegevens overzetten in een nieuw persoon
        // Vertrouw nooit de info die je vanuit de client kant binnenkrijgt!!
        Persoon nieuwPersoon = new Persoon(persoon);
        return service.postPersoon(nieuwPersoon);
    }

//    @PutMapping("personen/{id}")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public Persoon changePersoon(@PathVariable Long id, @RequestBody Persoon persoon) {      // Dit is de persoon die we in het bericht binnenkrijgen
//        System.out.println("LOG- PUT: personen - Aanroep van onze restserivce voor het toevoegen van één persoon.");
//
//        return service.putPersoon(id, persoon);
//        // Eigenlijk moeten we de persoon die binnenkomt, qua gegevens overzetten in een nieuw persoon
//        // Vertrouw nooit de info die je vanuit de client kant binnenkrijgt!!
////        Persoon nieuwPersoon = new Persoon(persoon);
////        return service.postPersoon(nieuwPersoon);
//    }
}
