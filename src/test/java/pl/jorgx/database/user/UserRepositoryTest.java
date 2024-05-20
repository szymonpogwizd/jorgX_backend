package pl.jorgx.database.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.jorgX.database.city.CityRepository;
import pl.jorgX.database.opinion.OpinionRepository;
import pl.jorgX.database.place.PlaceRepository;
import pl.jorgX.database.user.UserDAO;
import pl.jorgX.database.user.UserRepository;
import pl.jorgX.database.user.UserType;
import pl.jorgx.database.user.factory.UserDAOFactory;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OpinionRepository opinionRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private CityRepository cityRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        opinionRepository.deleteAll();
        placeRepository.deleteAll();
        cityRepository.deleteAll();
    }

    @Test
    public void saveUserTest() {
        // given
        UserDAO userDAO = UserDAOFactory.defaultBuilder().build();

        // when
        UserDAO savedUserDAO = userRepository.saveAndFlush(userDAO);

        // then
        assertNotNull(savedUserDAO.getId());
        assertEquals(userDAO, savedUserDAO);
    }

    @Test
    public void updateUserTest() {
        // given
        UserDAO userDAO = UserDAOFactory.defaultBuilder().build();
        userDAO.setName("oldName");

        // when
        userRepository.saveAndFlush(userDAO);
        userDAO.setName("newName");
        UserDAO savedUserDAO = userRepository.saveAndFlush(userDAO);

        // then
        assertNotNull(savedUserDAO);
        assertEquals(userDAO.getId(), savedUserDAO.getId());
        assertEquals("newName", savedUserDAO.getName());
    }

    @Test
    public void deleteUserTest() {
        // given
        UserDAO userDAO = UserDAOFactory.defaultBuilder().build();
        userRepository.saveAndFlush(userDAO);

        // when
        userRepository.delete(userDAO);
        Optional<UserDAO> deletedUserDAO = userRepository.findById(userDAO.getId());

        // then
        assertFalse(deletedUserDAO.isPresent());
    }

    @Test
    public void findAllUserTest() {
        // given
        UserDAO userDAO = UserDAOFactory.defaultBuilder().name("user1").email("user1@example.com").build();
        UserDAO userDAO2 = UserDAOFactory.defaultBuilder().name("user2").email("user2@example.com").build();
        userRepository.saveAndFlush(userDAO);
        userRepository.saveAndFlush(userDAO2);

        // when
        List<UserDAO> userDAOList = userRepository.findAll();

        // then
        assertEquals(2, userDAOList.size());
    }

    @Test
    public void findUserByUserNameOrEmailTest() {
        // given
        UserDAO user1 = UserDAOFactory.defaultBuilder().name("user1").email("user1@example.com").build();
        UserDAO user2 = UserDAOFactory.defaultBuilder().name("user2").email("user2@example.com").build();
        UserDAO user3 = UserDAOFactory.defaultBuilder().name("user3").email("user3@example.pl").build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        // when
        List<UserDAO> userUser1 = userRepository.findByUserNameOrEmail("user1");
        List<UserDAO> userExam = userRepository.findByUserNameOrEmail("exam");
        List<UserDAO> userCom = userRepository.findByUserNameOrEmail("com");

        // then
        assertEquals(1, userUser1.size());
        assertEquals(3, userExam.size());
        assertEquals(2, userCom.size());
    }

    @Test
    public void findUserByUserTypeTest() {
        // given
        UserDAO user1 = UserDAOFactory.defaultBuilder().userType(UserType.USER).name("User1").email("user1@example.com").build();
        UserDAO user2 = UserDAOFactory.defaultBuilder().userType(UserType.USER).name("User2").email("user2@example.com").build();
        UserDAO admin = UserDAOFactory.defaultBuilder().userType(UserType.ADMINISTRATOR).name("Administrator").email("admin@example.com").build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(admin);

        // when
        List<UserDAO> userTypeUser = userRepository.findByUserType(UserType.USER);
        List<UserDAO> userTypeAdministrator = userRepository.findByUserType(UserType.ADMINISTRATOR);

        // then
        assertEquals(2, userTypeUser.size());
        assertEquals(1, userTypeAdministrator.size());
        assertTrue(userTypeUser.contains(user1));
        assertTrue(userTypeUser.contains(user2));
        assertTrue(userTypeAdministrator.contains(admin));
    }

    @Test
    public void findUserByEmailTest() {
        // given
        UserDAO user1 = UserDAOFactory.defaultBuilder().email("user1@example.com").name("User1").build();
        UserDAO user2 = UserDAOFactory.defaultBuilder().email("user2@example.com").name("User2").build();

        userRepository.save(user1);
        userRepository.save(user2);

        // when
        Optional<UserDAO> userDAO = userRepository.findByEmail("user2@example.com");

        // then
        assertTrue(userDAO.isPresent());
        assertEquals(user2.getEmail(), userDAO.get().getEmail());
    }
}