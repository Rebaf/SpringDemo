package eu.additude.demo.controller;

import eu.additude.demo.dto.PersoonDTO;
import eu.additude.demo.exceptions.ResourceNotFoundException;
import eu.additude.demo.model.Persoon;
import eu.additude.demo.repository.PersoonRepository;
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

    public PersoonDTO findPersoonById(Long id) {
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

        return repository.save(persoon);
    }

//    public Persoon putPersoon(Long id, Persoon persoon) {
//        Optional<Persoon> persoonById = findPersoonById(id);
//    }
}