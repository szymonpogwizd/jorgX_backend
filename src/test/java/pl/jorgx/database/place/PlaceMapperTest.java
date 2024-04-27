package pl.jorgx.database.place;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.jorgX.database.place.*;
import pl.jorgx.database.configuration.MapperConfiguration;
import pl.jorgx.database.place.factory.PlaceDAOFactory;
import pl.jorgx.database.place.factory.PlaceDTOFactory;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MapperConfiguration.class)
public class PlaceMapperTest {

    @Autowired
    private PlaceMapper placeMapper;

    @Test
    void placeDAOToPlaceInfoDto()
    {
        PlaceDAO placeDAO = PlaceDAOFactory.defaultBuilder().build();

        PlaceInfoDTO placeInfoDTO = placeMapper.placeDAOToPlaceInfoDto(placeDAO);

        assertNotNull(placeInfoDTO);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(placeDAO.getName()).isEqualTo(placeInfoDTO.getName());
        softAssertions.assertThat(placeDAO.getStreet()).isEqualTo(placeInfoDTO.getStreet());
        softAssertions.assertThat(placeDAO.getRating()).isEqualTo(placeInfoDTO.getRating());
        softAssertions.assertThat(placeDAO.getOpeningHours()).isEqualTo(placeInfoDTO.getOpeningHours());
        softAssertions.assertThat(placeDAO.getCity()).isEqualTo(placeInfoDTO.getCity());
        softAssertions.assertThat(placeDAO.getId()).isEqualTo(placeInfoDTO.getId());
    }

    @Test
    void placeCreateDtoToPlaceDAO()
    {
        PlaceCreateDTO placeCreateDTO = PlaceDTOFactory.defaultPlaceCreateDTO();

        PlaceDAO placeDAO = placeMapper.placeCreateDtoToPlaceDAO(placeCreateDTO);

        assertNotNull(placeCreateDTO);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(placeDAO.getName()).isEqualTo(placeCreateDTO.getName());
        softAssertions.assertThat(placeDAO.getStreet()).isEqualTo(placeCreateDTO.getStreet());
        softAssertions.assertThat(placeDAO.getOpeningHours()).isEqualTo(placeCreateDTO.getOpeningHours());
    }

    @Test
    void placeUpdateDtoToPlaceDAO()
    {
        PlaceUpdateDTO placeUpdateDTO = PlaceDTOFactory.defaultPlaceUpdateDTO();

        PlaceDAO placeDAO = placeMapper.placeUpdateDtoToPlaceDAO(placeUpdateDTO);

        assertNotNull(placeUpdateDTO);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(placeDAO.getName()).isEqualTo(placeUpdateDTO.getName());
        softAssertions.assertThat(placeDAO.getStreet()).isEqualTo(placeUpdateDTO.getStreet());
        softAssertions.assertThat(placeDAO.getOpeningHours()).isEqualTo(placeUpdateDTO.getOpeningHours());
    }
}
