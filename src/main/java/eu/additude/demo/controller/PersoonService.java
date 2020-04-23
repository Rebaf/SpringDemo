package eu.additude.demo.controller;

import eu.additude.demo.model.Persoon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service // org.springframework
@Transactional // org.springframework
public class PersoonService {

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
    public Persoon findPersoonById(Long id) {
        return personen.get(id);
    }
}