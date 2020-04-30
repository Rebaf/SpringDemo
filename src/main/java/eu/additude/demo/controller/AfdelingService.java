package eu.additude.demo.controller;

import eu.additude.demo.exceptions.ResourceNotFoundException;
import eu.additude.demo.model.Afdeling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AfdelingService {
    
    @Autowired
    private AfdelingRepository repository;

    public Afdeling findAfdelingById(Long id) {
        Optional<Afdeling> afdelingOptional = repository.findById(id);
        if (!afdelingOptional.isPresent()) {
            throw new ResourceNotFoundException("Afdeling met id " + id + " niet gevonden");
        }
        return afdelingOptional.get();
    }

    public List<Afdeling> getAlleAfdelingen() {
        return repository.findAll();
    }

    public Afdeling postAfdeling(Afdeling Afdeling) {
        return repository.save(Afdeling);
    }
}
