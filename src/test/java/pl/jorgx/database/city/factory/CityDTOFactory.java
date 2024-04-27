package pl.jorgx.database.city.factory;

import pl.jorgX.database.city.CityCreateDTO;
import pl.jorgX.database.city.CityUpdateDTO;
import pl.jorgX.database.opinion.OpinionCreateDTO;
import pl.jorgX.database.opinion.OpinionUpdateDTO;
import pl.jorgx.database.opinion.factory.OpinionDAOFactory;

public class CityDTOFactory {

    public static CityCreateDTO defaultCityCreateDTO()
    {
        CityCreateDTO cityCreateDTO = new CityCreateDTO();
        cityCreateDTO.setName(CityDAOFactory.NAME);
        return cityCreateDTO;
    }


    public static CityUpdateDTO defaultCityUpdateDTO()
    {
        CityUpdateDTO cityUpdateDTO = new CityUpdateDTO();
        cityUpdateDTO.setName(CityDAOFactory.NAME);
        return cityUpdateDTO;
    }
}
