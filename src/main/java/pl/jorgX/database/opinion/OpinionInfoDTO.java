package pl.jorgX.database.opinion;

import lombok.Data;

import java.util.UUID;

@Data
public class OpinionInfoDTO {

    private UUID id;
    private String opinion;
    private String nick;
    private UUID placeId;
    private UUID userId;

}
