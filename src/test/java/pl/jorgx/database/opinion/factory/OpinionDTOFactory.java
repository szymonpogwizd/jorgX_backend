package pl.jorgx.database.opinion.factory;

import pl.jorgX.database.opinion.OpinionCreateDTO;
import pl.jorgX.database.opinion.OpinionUpdateDTO;
import pl.jorgx.database.place.factory.PlaceDAOFactory;

public class OpinionDTOFactory {

    public static OpinionCreateDTO defaultOpinionCreateDTO() {
        OpinionCreateDTO opinionCreateDTO = new OpinionCreateDTO();
        opinionCreateDTO.setEmail("test@example.com");
        opinionCreateDTO.setOpinion(OpinionDAOFactory.OPINION);
        opinionCreateDTO.setPlaceId(OpinionDAOFactory.PLACE);

        return opinionCreateDTO;
    }


    public static OpinionUpdateDTO defaultOpinionUpdateDTO() {
        OpinionUpdateDTO opinionUpdateDTO = new OpinionUpdateDTO();
        opinionUpdateDTO.setUserId(null);
        opinionUpdateDTO.setOpinion(OpinionDAOFactory.OPINION);
        opinionUpdateDTO.setPlaceId(OpinionDAOFactory.PLACE);

        return opinionUpdateDTO;
    }
}
