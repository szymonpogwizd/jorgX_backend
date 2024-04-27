package pl.jorgx.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.jorgX.database.user.UserDAO;
import pl.jorgX.database.user.UserRepository;
import pl.jorgX.database.user.UserType;
import pl.jorgX.services.UserService;
import pl.jorgX.validator.UserValidator;
import pl.jorgx.database.user.factory.UserDAOFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;

    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository, new UserValidator(userRepository));
    }

    @Test
    void create_newUser() {
        // given
        UserDAO userDAO = buildCreateUserDAO();

        when(userRepository.findByEmail(userDAO.getEmail())).thenReturn(Optional.empty());

        // when
        userService.create(userDAO);

        // then
        assertTrue(userDAO.getActive());
        assertNotNull(userDAO.getToken());
        assertNotNull(userDAO.getTokenExpiration());
    }

    @Test
    public void delete() {
        // given
        UUID id = UUID.randomUUID();
        UserDAO userDAO = UserDAOFactory.defaultBuilder().build();
        when(userRepository.existsById(id)).thenReturn(true);
        when(userRepository.findById(id)).thenReturn(Optional.of(userDAO));

        // when
        assertDoesNotThrow(() -> userService.delete(id));

        // then
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    void getAll() {
        // given
        List<UserDAO> userList = List.of(
                UserDAOFactory.defaultBuilder().build(),
                UserDAOFactory.defaultBuilder().build(),
                UserDAOFactory.defaultBuilder().build()
        );

        when(userRepository.findAll()).thenReturn(userList);

        // when
        List<UserDAO> userDAOList = userService.getAll();

        // then
        verify(userRepository, times(1)).findAll();
        assertEquals(3, userDAOList.size());
    }

    @Test
    void getAllUserTypes() {
        // given
        // when
        List<UserType> userTypes = List.of(UserType.values());

        // then
        assertEquals(2, userTypes.size());
        assertEquals(userTypes, userService.getAllUserTypes());
    }

    private UserDAO buildCreateUserDAO() {
        return UserDAOFactory.defaultBuilder().build();
    }
}