package eu.additude.demo.controller;

import eu.additude.demo.dto.AfdelingDTO;
import eu.additude.demo.exceptions.ResourceNotFoundException;
import eu.additude.demo.model.Afdeling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AfdelingService {
    
    @Autowired
    private AfdelingRepository repository;

    public AfdelingDTO findAfdelingById(Long id) {
//        Optional<Afdeling> afdelingOptional = repository.findById(id);
//        if (!afdelingOptional.isPresent()) {
//            throw new ResourceNotFoundException("Afdeling met id " + id + " niet gevonden");
//        }
//        Afdeling afdeling = afdelingOptional.get();
//        return new AfdelingDTO(afdeling);

        return repository.findById(id)
                .map(AfdelingDTO::new)
//                .map(afdeling -> new AfdelingDTO(afdeling))
                .orElseThrow(() -> new ResourceNotFoundException("Afdeling met id " + id + " niet gevonden"));
    }

    public List<AfdelingDTO> getAlleAfdelingen() {
        return repository.findAll()
                .stream()
                .map(afdeling -> new AfdelingDTO(afdeling))
                .collect(Collectors.toList());
    }

    public Afdeling postAfdeling(Afdeling Afdeling) {
        return repository.save(Afdeling);
    }
}
