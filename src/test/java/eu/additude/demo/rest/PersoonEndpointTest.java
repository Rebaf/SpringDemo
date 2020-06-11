package eu.additude.demo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.additude.demo.controller.PersoonService;
import eu.additude.demo.exceptions.ResourceNotFoundException;
import eu.additude.demo.model.Persoon;
import eu.additude.demo.utils.PersoonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersoonEndpoint.class)
public class PersoonEndpointTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersoonService service;

    @Test
    public void testGetPersoonById() throws Exception {
        when(service.findPersoonById(1L)).thenReturn(PersoonUtils.createPersoon(1L));
        mvc.perform(MockMvcRequestBuilders
                .get("/personen/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGetAllePersonen() throws Exception {
        when(service.getAllePersonen()).thenReturn(PersoonUtils.getPersonen());
        mvc.perform(MockMvcRequestBuilders
                .get("/personen")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(PersoonUtils.COLLECTION_COUNT)))
                .andExpect(jsonPath("$.[0].telefoonnummer", is("1234561")));
    }

    @Test
    public void testFindPersoonByIdNotFound() throws Exception {
        when(service.findPersoonById(1L)).thenThrow(new ResourceNotFoundException("Persoon met id 1 niet gevonden."));
        mvc.perform(MockMvcRequestBuilders
                .get("/personen/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateNewPersoon() throws Exception {
        Persoon persoon = PersoonUtils.createPersoon(0L);
        String postRequest = PersoonUtils.toJson(objectMapper, persoon);
        System.out.println(postRequest);
        mvc.perform(MockMvcRequestBuilders
                .post("/personen")
                .content(postRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    // Implement unit test voor getPersoonDTOById
}
