package pl.jorgX.database.city;


import lombok.Data;

import java.util.UUID;

@Data
public class cityInfoDto {

    private UUID id;

    private String city;

}