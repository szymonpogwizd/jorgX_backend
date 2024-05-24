package pl.jorgx.database.place.factory;

import pl.jorgX.database.place.PlaceCreateDTO;
import pl.jorgX.database.place.PlaceUpdateDTO;

import java.util.UUID;

public class PlaceDTOFactory {

    public static PlaceCreateDTO defaultPlaceCreateDTO() {
        PlaceCreateDTO placeCreateDTO = new PlaceCreateDTO();
        placeCreateDTO.setName(PlaceDAOFactory.NAME);
        placeCreateDTO.setStreet(PlaceDAOFactory.STREET);
        placeCreateDTO.setOpeningHours(PlaceDAOFactory.OPENING_HOURS);
        placeCreateDTO.setCityId(PlaceDAOFactory.ID);

        return placeCreateDTO;
    }

    public static PlaceUpdateDTO defaultPlaceUpdateDTO() {
        PlaceUpdateDTO placeUpdateDTO = new PlaceUpdateDTO();
        placeUpdateDTO.setCityId(PlaceDAOFactory.ID);
        placeUpdateDTO.setName(PlaceDAOFactory.NAME);
        placeUpdateDTO.setStreet(PlaceDAOFactory.STREET);
        placeUpdateDTO.setOpeningHours(PlaceDAOFactory.OPENING_HOURS);

        return placeUpdateDTO;
    }
}
