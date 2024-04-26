package pl.jorgX.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import pl.jorgX.database.city.CityRepository;
import pl.jorgX.database.opinion.OpinionDAO;
import pl.jorgX.database.opinion.OpinionInfoDTO;
import pl.jorgX.database.opinion.OpinionMapper;
import pl.jorgX.database.place.PlaceDAO;
import pl.jorgX.database.place.PlaceMapper;
import pl.jorgX.database.place.PlaceRepository;
import pl.jorgX.database.user.UserRepository;
import pl.jorgX.services.CityService;
import pl.jorgX.services.OpinionService;
import pl.jorgX.services.PlaceService;

import javax.validation.Valid;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/dashboard/general")
@RequiredArgsConstructor
@CrossOrigin
public class GeneralController {

    private final PlaceService placeService;
    private final CityService cityService;
    private final OpinionService opinionService;
    private final PlaceMapper placeMapper;
    private final CityRepository cityRepository;
    private final OpinionMapper opinionMapper;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    @PostMapping
    public OpinionInfoDTO createOpinionForNewPlace(@RequestBody @Valid GeneralDTO generalDTO) {
        log.debug("Creating opinion for new place");
        UUID cityId = cityService.getOrCreateCityId(generalDTO.getCity());

        PlaceDAO toCreate = placeMapper.placeCreateDtoToPlaceDAO(generalDTO.getPlace());
        cityRepository.findById(cityId).ifPresent(toCreate::setCity);
        toCreate.setName(generalDTO.getPlace().getName());
        toCreate.setStreet(generalDTO.getPlace().getStreet());
        toCreate.setOpeningHours(generalDTO.getPlace().getOpeningHours());
        placeService.createPlace(toCreate);

        OpinionDAO toCreateOpinion = opinionMapper.opinionCreateDtoToOpinionDAO(generalDTO.getOpinion());
        placeRepository.findById(toCreate.getId()).ifPresent(toCreateOpinion::setPlace);
        userRepository.findByEmail(generalDTO.getOpinion().getEmail()).ifPresent(toCreateOpinion::setUser);
        toCreateOpinion.setOpinion(generalDTO.getOpinion().getOpinion());
        opinionService.createOpinion(toCreateOpinion);

        placeService.updateRatingForPlace(toCreateOpinion.getPlace().getId());

        return log.traceExit(opinionMapper.opinionDAOToOpinionInfoDto(toCreateOpinion));
    }
}
