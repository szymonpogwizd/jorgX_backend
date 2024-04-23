package pl.jorgX.database.city;

import lombok.Data;

import java.util.UUID;

@Data
public class CityInfoDTO {

    private UUID id;
    private String name;
    private String description;

}
