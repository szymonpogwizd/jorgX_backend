package pl.jorgX.database.place;


import lombok.Data;

import java.util.UUID;

@Data
public class placeInfoDto {

    private UUID id;
    private String name;
    private UUID city_id;

}
