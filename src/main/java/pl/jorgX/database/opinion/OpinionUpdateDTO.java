package pl.jorgX.database.opinion;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class OpinionUpdateDTO {

    @NotBlank
    @Size(min = 1, max = 100)
    private String opinion;
    private String nick;
    private UUID place_id;
    private UUID user_id;
}
