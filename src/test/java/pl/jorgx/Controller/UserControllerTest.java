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
import pl.jorgX.database.user.UserCreateDTO;
import pl.jorgX.database.user.UserDAO;
import pl.jorgX.database.user.UserType;
import pl.jorgX.services.CityService;
import pl.jorgX.services.OpinionService;
import pl.jorgX.services.PlaceService;
import pl.jorgX.services.UserService;
import pl.jorgx.JsonUtility;
import pl.jorgx.database.configuration.MapperConfiguration;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = MapperConfiguration.class)
@RunWith(SpringRunner.class)
@WebMvcTest(UserControllerTest.class)
@WithMockUser( username = "user", roles = "USER")
public class UserControllerTest {

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

    @Test
    public void createUser_ok() throws Exception {
        UserDAO userDAO = UserDAOFactory.defaultBuilder().build();
        UserCreateDTO createDTO = UserDTOFactory.defaultUserCreateDTO();
        given(userService.create(any())).willReturn(userDAO);

        mockMvc.perform(post("/dashboard/users").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtility.toJson(createDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(userDAO.getEmail())))
                .andExpect(jsonPath("$.name", is(userDAO.getName())))
                .andExpect(jsonPath("$.userType", is(userDAO.getUserType().toString())))
                .andExpect(jsonPath("$.active",is(userDAO.getActive())));
    }

    @Test
    public void createUser_noValidInput() throws Exception
    {
        UserCreateDTO createDTO = UserDTOFactory.defaultUserCreateDTO();
        createDTO.setEmail(null);

        mockMvc.perform(post("/dashboard/users").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtility.toJson(createDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserType() throws Exception
    {
        List<UserType> userTypeList = Arrays.asList(UserType.values());
        when(userService.getAllUserTypes()).thenReturn(userTypeList);

        mockMvc.perform(get("/dashboard/users/userTypes").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$" , hasSize(userTypeList.size())))
                .andExpect(jsonPath("$[0]").value(UserType.USER.toString()))
                .andExpect(jsonPath("$[1]").value(UserType.ADMINISTRATOR.toString()));
    }

    @Test
    public void deleteuser() throws Exception
    {
        doNothing().when(userService).delete(any());

        mockMvc.perform(delete("/dashboard/users" + UUID.randomUUID()).with(csrf()))
                .andExpect(status().isOk());
    }
}
