package pl.jorgx.database.place;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.jorgX.database.opinion.OpinionRepository;
import pl.jorgX.database.place.PlaceDAO;
import pl.jorgX.database.place.PlaceRepository;
import pl.jorgx.database.place.factory.PlaceDAOFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PlaceRepositoryTest {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private OpinionRepository opinionRepository;


    @BeforeEach
    public void setUp() {
        placeRepository.deleteAll();
        opinionRepository.deleteAll();
    }

    @Test
    public void savePlace() {
        PlaceDAO placeDAO = PlaceDAOFactory.defaultBuilder().build();

        PlaceDAO saved = placeRepository.saveAndFlush(placeDAO);

        assertNotNull(saved.getId());
        assertEquals(placeDAO, saved);
    }

    @Test
    public void update() {
        PlaceDAO placeDAO = PlaceDAOFactory.defaultBuilder().build();
        placeDAO.setName("Old");

        placeRepository.saveAndFlush(placeDAO);
        placeDAO.setName("New");
        PlaceDAO saved = placeRepository.saveAndFlush(placeDAO);

        assertNotNull(saved);
        assertEquals(placeDAO.getId(), saved.getId());
        assertEquals("New", saved.getName());
    }

    @Test
    public void delete() {
        PlaceDAO placeDAO = PlaceDAOFactory.defaultBuilder().build();
        placeRepository.saveAndFlush(placeDAO);

        placeRepository.delete(placeDAO);
        PlaceDAO deleted = placeRepository.findById(placeDAO.getId()).orElse(null);

        assertNull(deleted);
    }

    @Test
    public void findall() {
        PlaceDAO placeDAO = PlaceDAOFactory.defaultBuilder().name("place1").build();
        PlaceDAO placeDAO2 = PlaceDAOFactory.defaultBuilder().name("place2").build();
        placeRepository.saveAndFlush(placeDAO);
        placeRepository.saveAndFlush(placeDAO2);

        List<PlaceDAO> all = placeRepository.findAll();

        assertEquals(2, all.size());
    }
}
