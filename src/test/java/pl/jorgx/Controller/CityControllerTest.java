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
import pl.jorgX.controller.CityController;
import pl.jorgX.database.city.CityCreateDTO;
import pl.jorgX.database.city.CityDAO;
import pl.jorgX.database.city.CityRepository;
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
import pl.jorgx.database.city.factory.CityDAOFactory;
import pl.jorgx.database.city.factory.CityDTOFactory;
import pl.jorgx.database.configuration.MapperConfiguration;
import pl.jorgx.database.place.factory.PlaceDAOFactory;
import pl.jorgx.database.place.factory.PlaceDTOFactory;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = MapperConfiguration.class)
@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
@WithMockUser(username = "user", roles = "USER")
public class CityControllerTest {

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
    public void createCity() throws Exception {

        CityDAO cityDAO = CityDAOFactory.defaultBuilder().build();
        CityCreateDTO cityCreateDTO = CityDTOFactory.defaultCityCreateDTO();
        given(cityService.createCity(any())).willReturn(cityDAO);

        mockMvc.perform(post("/dashboard/city").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtility.toJson(cityCreateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name" , is(cityDAO.getName())));
    }

    @Test
    public void getAll() throws Exception {

        List<CityDAO> list = CityDAOFactory.defaultList();
        given(cityService.getAll()).willReturn(list);

        mockMvc.perform(get("/dashboard/city").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(list.size())));
    }

    @Test
    public void deleteCity() throws Exception {
        doNothing().when(cityService).delete(any());

        mockMvc.perform(delete("/dashboard/city/{id}", UUID.randomUUID().toString())
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}