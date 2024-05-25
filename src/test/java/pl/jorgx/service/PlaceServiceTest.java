package pl.jorgx.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.jorgX.database.place.PlaceDAO;
import pl.jorgX.database.place.PlaceRepository;
import pl.jorgX.services.PlaceService;
import pl.jorgX.validator.PlaceValidator;
import pl.jorgx.database.place.factory.PlaceDAOFactory;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class PlaceServiceTest {

    private PlaceService placeService;

    private PlaceRepository placeRepository;

    @BeforeEach
    public void init() {
        placeRepository = Mockito.mock(PlaceRepository.class);
        placeService = new PlaceService(placeRepository, null, new PlaceValidator(placeRepository));
    }

    @Test
    void createnewPlace() {
        PlaceDAO placeDAO = buildCreatePlaceDAO();

        placeService.createPlace(placeDAO);

        verify(placeRepository, times(1)).save(placeDAO);
        assertNotNull(placeDAO);
    }

    @Test
    void delete() {
        UUID id = UUID.randomUUID();

        placeService.delete(id);

        verify(placeRepository, times(1)).deleteById(id);
    }

    @Test
    void getAll() {
        List<PlaceDAO> placeDAOS = List.of(
                PlaceDAOFactory.defaultBuilder().build(),
                PlaceDAOFactory.defaultBuilder().build(),
                PlaceDAOFactory.defaultBuilder().build()
        );

        when(placeRepository.findAll()).thenReturn(placeDAOS);

        List<PlaceDAO> placeDAOList = placeService.getAll();

        verify(placeRepository, times(1)).findAll();
        assertEquals(3, placeDAOList.size());
    }

    private PlaceDAO buildCreatePlaceDAO() {
        return PlaceDAOFactory.defaultBuilder().build();
    }
}
