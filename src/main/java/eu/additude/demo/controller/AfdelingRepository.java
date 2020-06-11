package eu.additude.demo.controller;

import eu.additude.demo.model.Afdeling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AfdelingRepository extends JpaRepository<Afdeling, Long> {
}
