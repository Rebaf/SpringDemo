package eu.additude.demo.controller;

import eu.additude.demo.dto.AfdelingDTO;
import eu.additude.demo.dto.PersoonDTO;
import eu.additude.demo.exceptions.ResourceNotFoundException;
import eu.additude.demo.model.Afdeling;
import eu.additude.demo.model.Persoon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersoonService {

    @Autowired
    private PersoonRepository repository;
    @Autowired
    private AfdelingService afdelingService;

    public Persoon findPersoonById(Long id) {
        Optional<Persoon> persoonOptional = repository.findById(id);
        if (!persoonOptional.isPresent()) {
            throw new ResourceNotFoundException("Persoon met id " + id + " niet gevonden");
        }
        Persoon persoon = persoonOptional.get();
        if (persoon.getAfdeling() != null) {
            persoon.setAfdelingId(persoon.getAfdeling().getId()); // aparte methode in Persoon
        }
        return persoon;
    }

    public PersoonDTO findPersoonDTOById(Long id) {
        Optional<Persoon> persoonOptional = repository.findById(id);
        if (!persoonOptional.isPresent()) {
            throw new ResourceNotFoundException("Persoon met id " + id + " niet gevonden");
        }
        return new PersoonDTO(persoonOptional.get());
    }

    public List<Persoon> getAllePersonen() {
        return repository.findAll();
    }

    public Persoon postPersoon(Persoon persoon) {
        if (persoon.getAfdelingId() != null) {
            // afdeling gaan koppelen. (alternatief is om dit direct via de afdelingRepository te doen.)
            Afdeling afdeling = afdelingService.findAfdelingById(persoon.getAfdelingId());
            persoon.setAfdeling(afdeling);
        } // Eventueel badrequest als er geen afdeling is ingevuld => keuze
        return repository.save(persoon);
    }

//    public Persoon putPersoon(Long id, Persoon persoon) {
//        Optional<Persoon> persoonById = findPersoonById(id);
//    }
}