package pl.jorgX.database.city;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CityCreateDTO {

    @NotBlank
    @Size(min = 1, max = 100)
    private String city;
}
