package pl.jorgX.controller;

import lombok.Data;
import pl.jorgX.database.city.CityCreateDTO;
import pl.jorgX.database.opinion.OpinionCreateDTO;
import pl.jorgX.database.place.PlaceCreateDTO;

import javax.validation.Valid;

@Data
public class GeneralDTO {

    @Valid
    private OpinionCreateDTO opinion;

    @Valid
    private CityCreateDTO city;

    @Valid
    private PlaceCreateDTO place;
}
