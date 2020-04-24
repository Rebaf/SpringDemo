package eu.additude.demo.controller;

import eu.additude.demo.model.Persoon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service        // org.springframework
@Transactional  // org.springframework
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

    // Nep db gemaakt
    private static Map<Long, Persoon> personen;
    static {
        personen = new HashMap<Long, Persoon>() {
            {
                put(1L, new Persoon(1L, "123123", "Cor", "", "Faber", "0612345678"));
                put(2L, new Persoon(2L, "123124", "Rutger", "", "Spaans", "0612345679"));
                put(3L, new Persoon(3L, "123125", "Martin", "", "Berends", "0612345670"));
            }
        };
    }
    public Persoon findPersoonByIdIntern(Long id) {
        return personen.get(id);
    }

    public List<Persoon> getAllePersonenIntern() {
//        Set<Long> longs = personen.keySet(); // alle keys eruit halen
//        Collection<Persoon> values = personen.values(); // alle elementen die bij de keys horen eruit halen
//        List<Persoon>persoonLijst = new ArrayList<>(values); // van die collections een ArrayList maken

        return new ArrayList<Persoon>(personen.values());
    }
}