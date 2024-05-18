package pl.jorgx.database.city;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.jorgX.database.city.CityDAO;
import pl.jorgX.database.city.CityRepository;
import pl.jorgX.database.opinion.OpinionDAO;
import pl.jorgX.database.opinion.OpinionRepository;
import pl.jorgx.database.city.factory.CityDAOFactory;
import pl.jorgx.database.opinion.factory.OpinionDAOFactory;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@Transactional
public class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;


    @Test
    public void saveCity()
    {
        CityDAO cityDAO = CityDAOFactory.defaultBuilder().build();

        CityDAO saved = cityRepository.saveAndFlush(cityDAO);

        assertNotNull(saved.getId());
        assertEquals(cityDAO, saved);
    }

    @Test
    public void update()
    {
        CityDAO cityDAO = CityDAOFactory.defaultBuilder().build();
        cityDAO.setName("Old");

        cityRepository.saveAndFlush(cityDAO);
        cityDAO.setName("New");
        CityDAO saved = cityRepository.saveAndFlush(cityDAO);

        assertNotNull(saved);
        assertEquals(cityDAO.getId(), saved.getId());
        assertEquals("New",saved.getName());
    }

    @Test
    public void delete()
    {
        CityDAO cityDAO = CityDAOFactory.defaultBuilder().build();
        cityRepository.saveAndFlush(cityDAO);

        cityRepository.delete(cityDAO);
        CityDAO deleted = cityRepository.findById(cityDAO.getId()).orElse(null);

        assertNull(deleted);
    }

    @Test
    public void setUp()
    {
        cityRepository.deleteAll();
    }

    @Test
    public void findall()
    {
        CityDAO cityDAO = CityDAOFactory.defaultBuilder().name("place1").build();
        CityDAO cityDAO2 = CityDAOFactory.defaultBuilder().name("place2").build();
        cityRepository.saveAndFlush(cityDAO);
        cityRepository.saveAndFlush(cityDAO2);

        List<CityDAO> all = cityRepository.findAll();

        assertEquals(4,all.size());
    }
}
