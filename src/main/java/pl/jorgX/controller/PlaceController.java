package pl.jorgX.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.jorgX.database.city.CityRepository;
import pl.jorgX.database.place.*;
import pl.jorgX.services.PlaceService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/dashboard/place")
@RequiredArgsConstructor
@CrossOrigin
public class PlaceController {

    private final PlaceService placeService;
    private final PlaceMapper placeMapper;
    private final CityRepository cityRepository;

    @PostMapping
    public PlaceInfoDTO createPlace(@RequestBody @Valid PlaceCreateDTO place) {
        log.debug("Create place {}", place);
        PlaceDAO toCreate = placeMapper.placeCreateDtoToPlaceDAO(place);
        cityRepository.findById(place.getCityId()).ifPresent(toCreate::setCity);
        toCreate.setName(place.getName());
        PlaceDAO createdPlace = placeService.createPlace(toCreate);
        return log.traceExit(placeMapper.placeDAOToPlaceInfoDto(createdPlace));
    }

    @GetMapping
    public List<PlaceInfoDTO> getAll() {
        log.debug("Getting all places");
        return log.traceExit(placeService.getAll()
                .stream()
                .map(placeMapper::placeDAOToPlaceInfoDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{name}")
    public PlaceInfoDTO getPlaceByName(@PathVariable String name) {
        log.debug("Getting place by name: {}", name);
        return log.traceExit(placeService.getPlaceByName(name)
                .map(placeMapper::placeDAOToPlaceInfoDto)
                .orElseThrow(() -> new RuntimeException("Place not found")));
    }

    @GetMapping("/city/{id}")
    public List<PlaceInfoDTO> getPlaceByCityId(@PathVariable UUID id) {
        log.debug("Getting place by city id: {}", id);
        return log.traceExit(placeService.getPlaceByCityId(id)
                .stream()
                .map(placeMapper::placeDAOToPlaceInfoDto)
                .collect(Collectors.toList()));
    }

    @PutMapping("{id}")
    public PlaceInfoDTO updatePlace(@RequestBody @Valid PlaceUpdateDTO place, @PathVariable UUID id)
    {
        log.debug("Update place {}: {}",id,place);
        PlaceDAO updatePlace = placeService.update(id, placeMapper.placeUpdateDtoToPlaceDAO(place));
        PlaceInfoDTO updateInfoPlace = placeMapper.placeDAOToPlaceInfoDto(updatePlace);
        return  log.traceExit(updateInfoPlace);
    }

    @DeleteMapping("{id}")
    public void deleteOpinion(@PathVariable UUID id) {
        log.debug("Deleting place {}", id);
        placeService.delete(id);
    }
}
