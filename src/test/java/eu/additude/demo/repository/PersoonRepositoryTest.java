package eu.additude.demo.repository;

import eu.additude.demo.controller.PersoonRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersoonRepositoryTest {

    @Autowired
    private PersoonRepository repository;

    // Implement test methods findPersoonById, getAllePersonen
    // hint : test de methods van de repository findById en findAll
}
