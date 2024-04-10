package pl.jorgX.database.place;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class PlaceUpdateDTO {

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;
    private UUID city_id;
}
