package pl.jorgX.database.city;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CityUpdateDTO {

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;
    private String description;
}
