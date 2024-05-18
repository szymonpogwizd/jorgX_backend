package pl.jorgx.database.opinion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.jorgX.database.opinion.OpinionDAO;
import pl.jorgX.database.opinion.OpinionRepository;
import pl.jorgX.database.place.PlaceDAO;
import pl.jorgX.database.place.PlaceRepository;
import pl.jorgx.database.opinion.factory.OpinionDAOFactory;
import pl.jorgx.database.place.factory.PlaceDAOFactory;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@Transactional
public class OpinionRepositoryTest {

    @Autowired
    private OpinionRepository opinionRepository;


    @BeforeEach
    public void setUp()
    {
        opinionRepository.deleteAll();
    }

    @Test
    public void saveOpinion()
    {
        OpinionDAO opinionDAO = OpinionDAOFactory.defaultBuilder().build();

        OpinionDAO saved = opinionRepository.saveAndFlush(opinionDAO);

        assertNotNull(saved.getId());
        assertEquals(opinionDAO, saved);
    }

    @Test
    public void update()
    {
        OpinionDAO opinionDAO = OpinionDAOFactory.defaultBuilder().build();
        opinionDAO.setOpinion("Old");

        opinionRepository.saveAndFlush(opinionDAO);
        opinionDAO.setOpinion("New");
        OpinionDAO saved = opinionRepository.saveAndFlush(opinionDAO);

        assertNotNull(saved);
        assertEquals(opinionDAO.getId(), saved.getId());
        assertEquals("New",saved.getOpinion());
    }

    @Test
    public void delete()
    {
        OpinionDAO opinionDAO = OpinionDAOFactory.defaultBuilder().build();
        opinionRepository.saveAndFlush(opinionDAO);

        opinionRepository.delete(opinionDAO);
        OpinionDAO deleted = opinionRepository.findById(opinionDAO.getId()).orElse(null);

        assertNull(deleted);
    }

    @Test
    public void findall()
    {
        OpinionDAO opinionDAO = OpinionDAOFactory.defaultBuilder().opinion("place1").build();
        OpinionDAO opinionDAO2 = OpinionDAOFactory.defaultBuilder().opinion("place2").build();
        opinionRepository.saveAndFlush(opinionDAO);
        opinionRepository.saveAndFlush(opinionDAO2);

        List<OpinionDAO> all = opinionRepository.findAll();

        assertEquals(2,all.size());
    }
}
