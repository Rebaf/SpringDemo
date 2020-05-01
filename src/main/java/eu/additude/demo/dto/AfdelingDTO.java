package eu.additude.demo.dto;

import eu.additude.demo.model.Afdeling;
import eu.additude.demo.model.Persoon;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AfdelingDTO {
    private Long id;
    private String naam;
    private List<Long> personenIds = new ArrayList<>();
    private List<PersoonDTO> personenDtos = new ArrayList<>();

    public AfdelingDTO(Afdeling afdeling) {
        id = afdeling.getId();
        naam = afdeling.getNaam();
        personenIds = afdeling.getPersonen()
                .stream()
                .map(persoon -> persoon.getId())
                .collect(Collectors.toList());

        personenDtos = afdeling.getPersonen()
                .stream()
                .map(persoon -> new PersoonDTO(persoon))
                .collect(Collectors.toList());
    }
}
