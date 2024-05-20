package pl.jorgx.database.city;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.jorgX.database.city.*;
import pl.jorgx.database.city.factory.CityDAOFactory;
import pl.jorgx.database.city.factory.CityDTOFactory;
import pl.jorgx.database.configuration.MapperConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MapperConfiguration.class)
public class CityMapperTest {

    @Autowired
    private CityMapper cityMapper;

    @Test
    void cityDAOToCityInfoDto() {
        CityDAO cityDAO = CityDAOFactory.defaultBuilder().build();

        CityInfoDTO cityInfoDTO = cityMapper.cityDAOToCityInfoDto(cityDAO);

        assertNotNull(cityInfoDTO);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(cityDAO.getName()).isEqualTo(cityInfoDTO.getName());
        softAssertions.assertThat(cityDAO.getId()).isEqualTo(cityInfoDTO.getId());
    }

    @Test
    void cityCreateDtoToCityDAO() {
        CityCreateDTO cityCreateDTO = CityDTOFactory.defaultCityCreateDTO();

        CityDAO cityDAO = cityMapper.cityCreateDtoToCityDAO(cityCreateDTO);

        assertNotNull(cityDAO);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(cityDAO.getName()).isEqualTo(cityCreateDTO.getName());
    }

    @Test
    void cityUpdateDtoToCityDAO() {
        CityUpdateDTO cityUpdateDTO = CityDTOFactory.defaultCityUpdateDTO();

        CityDAO cityDAO = cityMapper.cityUpdateDtoToCityDAO(cityUpdateDTO);

        assertNotNull(cityDAO);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(cityDAO.getName()).isEqualTo(cityUpdateDTO.getName());
    }
}