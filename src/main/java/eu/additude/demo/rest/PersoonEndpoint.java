package eu.additude.demo.rest;

import eu.additude.demo.controller.PersoonService;
import eu.additude.demo.model.Persoon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersoonEndpoint {
    @Autowired
    PersoonService service; //= new PersoonService();

    @GetMapping("getPersoon/{id}")  // org.springframework
    public @ResponseBody ResponseEntity<Persoon> getPersoonById(@PathVariable Long id) {
        System.out.println("LOG- REST: getPersoonById/" + id + " - Aanroep van onze restserivce voor het opvragen van één persoon.");
        Persoon persoon = service.findPersoonById(id);

        if (persoon != null) {
            return new ResponseEntity<>(persoon, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}