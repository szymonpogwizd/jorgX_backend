package pl.jorgX.database.opinion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class OpinionUpdateDTO {

    @NotBlank
    @Size(min = 1, max = 100)
    private String opinion;
    private String nick;
    private UUID place_id;
}
