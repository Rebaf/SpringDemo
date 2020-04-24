package eu.additude.demo.controller;

import eu.additude.demo.model.Persoon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersoonRepository extends JpaRepository<Persoon, Long> {
}

//          Create (of insert)  : Toevoegen van nieuwe gegevens.
//          Read (of select)    : Opvragen van gegevens.
//          Update              : Wijzigen van gegevens.
//          Delete              : Verwijderen van gegevens.
