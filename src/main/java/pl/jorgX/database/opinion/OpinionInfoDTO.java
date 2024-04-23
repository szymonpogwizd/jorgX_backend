package pl.jorgX.database.opinion;

import lombok.Data;
import pl.jorgX.database.place.PlaceDAO;
import pl.jorgX.database.user.UserDAO;

import java.util.UUID;

@Data
public class OpinionInfoDTO {

    private UUID id;
    private String opinion;
    private String nick;
    private PlaceDAO place;
    private UserDAO user;
}
