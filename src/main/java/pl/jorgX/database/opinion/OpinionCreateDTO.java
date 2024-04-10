package pl.jorgX.database.opinion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class OpinionCreateDTO {

    @NotBlank
    @Size(min = 1, max = 100)
    private String opinion;
    private String nick;
    private UUID placeId;
}
