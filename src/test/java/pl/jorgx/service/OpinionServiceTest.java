package pl.jorgx.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.jorgX.database.opinion.OpinionDAO;
import pl.jorgX.database.opinion.OpinionRepository;
import pl.jorgX.database.opinion.OpinionType;
import pl.jorgX.database.place.PlaceDAO;
import pl.jorgX.database.user.UserDAO;
import pl.jorgX.services.AIModelService;
import pl.jorgX.services.OpinionService;
import pl.jorgX.validator.OpinionValid;
import pl.jorgx.database.opinion.factory.OpinionDAOFactory;
import pl.jorgx.database.place.factory.PlaceDAOFactory;
import pl.jorgx.database.user.factory.UserDAOFactory;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class OpinionServiceTest {

    private OpinionService opinionService;
    private OpinionRepository opinionRepository;

    @BeforeEach
    public void init()
    {
        opinionRepository = Mockito.mock(OpinionRepository.class);
        opinionService = new OpinionService(opinionRepository, new AIModelService(),new OpinionValid(opinionRepository));
    }

    @Test
    void create()
    {
        UserDAO userDAO = UserDAOFactory.defaultBuilder().build();
        userDAO.setId(UUID.randomUUID());
        PlaceDAO placeDAO = PlaceDAOFactory.defaultBuilder().build();
        placeDAO.setId(UUID.randomUUID());
        OpinionDAO opinionDAO = OpinionDAOFactory.defaultBuilder().build();
        opinionDAO.setUser(userDAO);
        opinionDAO.setPlace(placeDAO);
        opinionDAO.setOpinionType(OpinionType.POSITIVE);

        opinionService.createOpinion(opinionDAO);

        verify(opinionRepository, times(1)).save(opinionDAO);
        assertNotNull(opinionDAO);
    }

    @Test
    void delete()
    {
        UUID id = UUID.randomUUID();

        opinionService.delete(id);

        verify(opinionRepository, times(1)).deleteById(id);
    }

}
