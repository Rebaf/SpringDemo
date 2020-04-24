package eu.additude.demo.controller;

import eu.additude.demo.model.Persoon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service        // org.springframework
@Transactional  // org.springframework
public class PersoonNoDbService {

    public Persoon findPersoonByIdNoDb(Long id) {
        return personen.get(id);
    }

    public List<Persoon> getAllePersonenNoDb() {
//        Set<Long> longs = personen.keySet(); // alle keys eruit halen
//        Collection<Persoon> values = personen.values(); // alle elementen die bij de keys horen eruit halen
//        List<Persoon>persoonLijst = new ArrayList<>(values); // van die collections een ArrayList maken

        return new ArrayList<Persoon>(personen.values());
    }

    // Nep db voor uitvragen Personen (dus nog niet via een repository)
    private static Map<Long, Persoon> personen;

    static {
        personen = new HashMap<Long, Persoon>() {
            {
                put(1L, new Persoon(1L, "123123", "Michelle", "", "Schrier", "06-56913497"));
                put(2L, new Persoon(2L, "123124", "Aldo", "", "Koenes", "06-35415759"));
                put(3L, new Persoon(3L, "123125", "Melika", "", "Verlinde", "06-18157543"));
            }
        };
    }
}