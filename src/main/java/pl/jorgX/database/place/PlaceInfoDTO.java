package pl.jorgX.database.place;


import lombok.Data;

import java.util.UUID;

@Data
public class PlaceInfoDTO {

    private UUID id;
    private String name;
    private String street;
    private String openingHours;
    private Double rating;
    private UUID cityId;
}
