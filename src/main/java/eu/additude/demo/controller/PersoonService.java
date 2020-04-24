package eu.additude.demo.controller;

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

    public Optional<Persoon> findPersoonById(Long id) {
        Optional<Persoon> persoonOptional = repository.findById(id);
        return persoonOptional;
    }

    public List<Persoon> getAllePersonen() {
        return repository.findAll();
    }

    public Persoon postPersoon(Persoon persoon) {
        return repository.save(persoon);
    }
}