package pl.jorgX.database.opinion;


import lombok.Data;

import java.util.UUID;

@Data
public class OpinionInfoDto {

    private UUID id;

    private String opinion;

    private String nick;

    private UUID placeId;


}
