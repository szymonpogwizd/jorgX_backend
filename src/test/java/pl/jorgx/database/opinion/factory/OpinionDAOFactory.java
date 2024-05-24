package pl.jorgx.database.opinion.factory;

import pl.jorgX.database.opinion.OpinionDAO;
import pl.jorgX.database.opinion.OpinionType;
import pl.jorgX.database.place.PlaceDAO;

import java.util.List;
import java.util.UUID;

import static pl.jorgX.database.opinion.OpinionType.NEGATIVE;

public class OpinionDAOFactory {

    public static final String OPINION = "test";
    public static final OpinionType opinionType = NEGATIVE;
    public static final UUID PLACE = UUID.randomUUID();

    public static OpinionDAO.OpinionDAOBuilder defaultBuilder() {
        return OpinionDAO.builder()
                .opinion(OPINION)
                .opinionType(opinionType);
    }

    public static List<OpinionDAO> defaultList() {
        return List.of(
                defaultBuilder().build(),
                defaultBuilder().build()
        );
    }
}
