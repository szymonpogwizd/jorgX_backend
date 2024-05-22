package pl.jorgx.Controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.yaml.snakeyaml.error.YAMLException;
import pl.jorgX.controller.OpinionController;
import pl.jorgX.controller.PlaceController;
import pl.jorgX.database.city.CityRepository;
import pl.jorgX.database.opinion.OpinionCreateDTO;
import pl.jorgX.database.opinion.OpinionDAO;
import pl.jorgX.database.opinion.OpinionRepository;
import pl.jorgX.database.place.PlaceCreateDTO;
import pl.jorgX.database.place.PlaceDAO;
import pl.jorgX.database.place.PlaceRepository;
import pl.jorgX.database.user.UserRepository;
import pl.jorgX.services.CityService;
import pl.jorgX.services.OpinionService;
import pl.jorgX.services.PlaceService;
import pl.jorgX.services.UserService;
import pl.jorgx.JsonUtility;
import pl.jorgx.database.configuration.MapperConfiguration;
import pl.jorgx.database.opinion.factory.OpinionDAOFactory;
import pl.jorgx.database.opinion.factory.OpinionDTOFactory;
import pl.jorgx.database.place.factory.PlaceDAOFactory;
import pl.jorgx.database.place.factory.PlaceDTOFactory;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration(classes = MapperConfiguration.class)
@RunWith(SpringRunner.class)
@WebMvcTest(OpinionController.class)
@WithMockUser(username = "user", roles = "USER")
public class OpinionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private CityService cityService;

    @MockBean
    private OpinionService opinionService;

    @MockBean
    private PlaceService placeService;

    @MockBean
    private OpinionRepository opinionRepository;

    @MockBean
    private CityRepository cityRepository;

    @MockBean
    private PlaceRepository placeRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void createOpinion() throws Exception {
        // Create a mock OpinionDAO object
        OpinionDAO opinionDAO = OpinionDAOFactory.defaultBuilder().build();

        // Set a valid place for the opinionDAO
        PlaceDAO placeDAO = PlaceDAOFactory.defaultBuilder().build(); // You may need to create a mock PlaceDAO object
        opinionDAO.setPlace(placeDAO);

        // Create a mock OpinionCreateDTO object
        OpinionCreateDTO opinionCreateDTO = OpinionDTOFactory.defaultOpinionCreateDTO();

        // Mock the behavior of opinionService.createOpinion(any()) to return the mock OpinionDAO object
        given(opinionService.createOpinion(any())).willReturn(opinionDAO);

        // Perform the POST request to create an opinion
        mockMvc.perform(post("/dashboard/opinion").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtility.toJson(opinionCreateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.opinion", is(opinionDAO.getOpinion())))
                .andExpect(jsonPath("$.opinionType", is("NEGATIVE")));
    }

    @Test
    public void deleteOpinion() throws Exception {
        // Mockowanie zachowania opinionService.delete()
        doThrow(new YAMLException("Place not found")).when(opinionService).delete(any());

        mockMvc.perform(delete("/dashboard/opinion/{id}", UUID.randomUUID().toString()).with(csrf()))
                .andExpect(status().isNotFound()); // Oczekujemy statusu 404
    }
}
