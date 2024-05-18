package pl.jorgx.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.jorgX.database.city.CityDAO;
import pl.jorgX.database.city.CityRepository;
import pl.jorgX.services.CityService;
import pl.jorgX.validator.CityValidator;
import pl.jorgx.database.city.factory.CityDAOFactory;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class CityServiceTest {

    private CityService cityService;
    private CityRepository cityRepository;

    @BeforeEach
    public void init()
    {
        cityRepository = Mockito.mock(CityRepository.class);
        cityService = new CityService(cityRepository,null,new CityValidator(cityRepository));
    }

    @Test
    void create()
    {
        CityDAO cityDAO = CityDAOFactory.defaultBuilder().build();

        cityService.createCity(cityDAO);

        verify(cityRepository, times(1)).save(cityDAO);
        assertNotNull(cityDAO);
    }

    @Test
    void delete()
    {
        UUID uuid = UUID.randomUUID();

        cityService.delete(uuid);

        verify(cityRepository, times(1)).deleteById(uuid);
    }

    @Test
    void getall()
    {
        List<CityDAO> cityDAOList = List.of(
                CityDAOFactory.defaultBuilder().build(),
                CityDAOFactory.defaultBuilder().build(),
                CityDAOFactory.defaultBuilder().build()
        );

        when(cityRepository.findAll()).thenReturn(cityDAOList);

        List<CityDAO> cityDAOS = cityService.getAll();

        verify(cityRepository, times(1)).findAll();
        assertEquals(3, cityDAOS.size());
    }
}
