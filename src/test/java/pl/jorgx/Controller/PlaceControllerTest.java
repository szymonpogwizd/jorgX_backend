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
import pl.jorgX.controller.PlaceController;
import pl.jorgX.controller.UserController;
import pl.jorgX.database.city.CityRepository;
import pl.jorgX.database.place.PlaceCreateDTO;
import pl.jorgX.database.place.PlaceDAO;
import pl.jorgX.database.place.PlaceRepository;
import pl.jorgX.database.user.UserCreateDTO;
import pl.jorgX.database.user.UserDAO;
import pl.jorgX.database.user.UserRepository;
import pl.jorgX.database.user.UserType;
import pl.jorgX.services.CityService;
import pl.jorgX.services.OpinionService;
import pl.jorgX.services.PlaceService;
import pl.jorgX.services.UserService;
import pl.jorgx.JsonUtility;
import pl.jorgx.database.configuration.MapperConfiguration;
import pl.jorgx.database.place.factory.PlaceDAOFactory;
import pl.jorgx.database.place.factory.PlaceDTOFactory;
import pl.jorgx.database.user.factory.UserDAOFactory;
import pl.jorgx.database.user.factory.UserDTOFactory;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = MapperConfiguration.class)
@RunWith(SpringRunner.class)
@WebMvcTest(PlaceController.class)
@WithMockUser(username = "user", roles = "USER")
public class PlaceControllerTest {

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
    private PlaceRepository placeRepository;

    @MockBean
    private CityRepository cityRepository;

    @Test
    public void createPlace() throws Exception {

        PlaceDAO placeDAO = PlaceDAOFactory.defaultBuilder().build();
        PlaceCreateDTO placeCreateDTO = PlaceDTOFactory.defaultPlaceCreateDTO();
        given(placeService.createPlace(any())).willReturn(placeDAO);

        mockMvc.perform(post("/dashboard/place").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtility.toJson(placeCreateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name" , is(placeDAO.getName())))
                .andExpect(jsonPath("$.street", is(placeDAO.getStreet())))
                .andExpect(jsonPath("$.openingHours", is(placeDAO.getOpeningHours())))
                .andExpect(jsonPath("$.rating", is(placeDAO.getRating())));
    }

    @Test
    public void getAll() throws Exception {

        List<PlaceDAO> list = PlaceDAOFactory.defaultList();
        given(placeService.getAll()).willReturn(list);

        mockMvc.perform(get("/dashboard/place").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(list.size())));
    }

    @Test
    public void deletePlace() throws Exception {
        doNothing().when(placeService).delete(any());

        mockMvc.perform(delete("/dashboard/place/{id}", UUID.randomUUID().toString())
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}
