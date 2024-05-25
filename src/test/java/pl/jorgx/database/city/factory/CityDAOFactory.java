package pl.jorgx.database.city.factory;

import pl.jorgX.database.city.CityDAO;
import pl.jorgX.database.opinion.OpinionDAO;
import pl.jorgX.database.opinion.OpinionType;

import java.util.List;
import java.util.UUID;

import static pl.jorgX.database.opinion.OpinionType.NEGATIVE;

public class CityDAOFactory {

    public static final String NAME = "test";

    public static CityDAO.CityDAOBuilder defaultBuilder() {
        return CityDAO.builder()
                .name(NAME);
    }

    public static List<CityDAO> defaultList() {
        return List.of(
                defaultBuilder().build(),
                defaultBuilder().build()
        );
    }
}
