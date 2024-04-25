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
    private UUID placeId;
    private UUID userId;
}
