package eu.additude.demo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.additude.demo.dto.PersoonDTO;
import eu.additude.demo.model.Persoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersoonUtils {

    public static int COLLECTION_COUNT = 5;

    public static Persoon createPersoon(Long id) {
        Persoon persoon = new Persoon();
        if (id != 0) {
            persoon.setId(id);
        }
        persoon.setAchternaam("naam-" + id);
//        persoon.setAfdeling(1);
        persoon.setLeeftijd(18 + new Random().nextInt(17));
        persoon.setBsn("1.234.567");
        persoon.setVoornaam("voornaam-" + id);
        persoon.setTelefoonnummer("123456" + id);
        persoon.setTussenvoegsel("den");

        return persoon;
    }

    public static PersoonDTO createPersoonDTO() {
        return new PersoonDTO(createPersoon(1L));
    }

    public static List<Persoon> getPersonen() {
        List<Persoon> personen = new ArrayList<>();
        for (long i = 1; i <= COLLECTION_COUNT; i++) {
            personen.add(createPersoon(i));
        }
        return personen;
    }

    public static String toJson(final ObjectMapper objectMapper, final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
