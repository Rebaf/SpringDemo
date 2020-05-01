package eu.additude.demo.controller;

import eu.additude.demo.dto.AfdelingDTO;
import eu.additude.demo.exceptions.ConflictException;
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

    public AfdelingDTO findAfdelingDTOById(Long id) {
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

    public Afdeling findAfdelingById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Afdeling met id " + id + " niet gevonden"));
    }

    public List<AfdelingDTO> getAlleAfdelingen() {
        return repository.findAll()
                .stream()
                .map(afdeling -> new AfdelingDTO(afdeling))
                .collect(Collectors.toList());
    }

    public Afdeling postAfdeling(Afdeling afdeling) {
        return repository.save(afdeling);
    }

    public Afdeling putAfdeling(Long id, Afdeling afdeling) {
        if (!id.equals(afdeling.getId())) {
            throw new ConflictException("Id " + id + " komt niet overeen met de gevonden id van afdeling.");
        }
        Afdeling target = findAfdelingById(id);
        target.setNaam(afdeling.getNaam());
        return repository.save(target);
    }

    public void deleteAfdeling(Long id) {
        Afdeling afdeling = findAfdelingById(id);
        repository.delete(afdeling);
    }
}
