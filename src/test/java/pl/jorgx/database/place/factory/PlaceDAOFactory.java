package pl.jorgx.database.place.factory;

import pl.jorgX.database.city.CityDAO;
import pl.jorgX.database.place.PlaceDAO;

import java.util.List;
import java.util.UUID;

public class PlaceDAOFactory {

    public static final String NAME = "test";
    public static final String STREET = "TEST 123";
    public static final String OPENING_HOURS = "12:00 - 22:00";
    public static final Double RATING = 4.5;
    public static final UUID ID = UUID.randomUUID();

    public static PlaceDAO.PlaceDAOBuilder defaultBuilder()
    {
        return  PlaceDAO.builder()
                .name(NAME)
                .street(STREET)
                .openingHours(OPENING_HOURS)
                .rating(RATING);
    }

    public static List<PlaceDAO> defaultList()
    {
        return List.of(
                defaultBuilder().name("Test1").build(),
                defaultBuilder().name("Test2").build()
        );
    }
}
