package pl.jorgX.database.place;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class PlaceCreateDTO {
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;
    private UUID cityId;
}
