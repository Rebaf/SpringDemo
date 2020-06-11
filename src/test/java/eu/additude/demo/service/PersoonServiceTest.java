package eu.additude.demo.service;

import eu.additude.demo.controller.PersoonRepository;
import eu.additude.demo.controller.PersoonService;
import eu.additude.demo.exceptions.ResourceNotFoundException;
import eu.additude.demo.model.Persoon;
import eu.additude.demo.utils.PersoonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PersoonServiceTest {

    @Mock
    private PersoonRepository repository;

    @InjectMocks
    private PersoonService service;

    @Test
    public void testFindPersoonById() throws Exception {
        Persoon persoon = PersoonUtils.createPersoon(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(persoon));
        Persoon p = service.findPersoonById(1L);
        assertNotNull(p);
        assertTrue(p.getId() == persoon.getId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testFindPersoonByIdNotFound() throws Exception {
        when(repository.findById(1L)).thenThrow(new ResourceNotFoundException("Persoon met id 1 niet gevonden"));
        Persoon p = service.findPersoonById(1L);
    }

    @Test
    public void testGetAllePersonen() {
        when(repository.findAll()).thenReturn(PersoonUtils.getPersonen());
        List<Persoon> personen = service.getAllePersonen();
        assertTrue(personen.size() == PersoonUtils.COLLECTION_COUNT);
    }

    // Implement unit test voor findPersoonDTOById

    // Implement unit test voor savePersoon
}
