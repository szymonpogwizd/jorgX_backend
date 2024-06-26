package pl.jorgX.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
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
        toCreate.setStreet(place.getStreet());
        toCreate.setOpeningHours(place.getOpeningHours());
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

    @GetMapping("/checkPlaceExists")
    public ResponseEntity<Boolean> checkPlaceExists(@RequestParam String street, @RequestParam String name) {
        log.debug("Checking if place exists by street: {} and name: {}", street, name);
        boolean exists = placeService.placeExists(street, name);
        return ResponseEntity.ok(exists);
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
    public PlaceInfoDTO updatePlace(@RequestBody @Valid PlaceUpdateDTO place, @PathVariable UUID id) {
        log.debug("Update place {}: {}", id, place);
        PlaceDAO updatedPlace = placeService.update(id, placeMapper.placeUpdateDtoToPlaceDAO(place));
        return log.traceExit(placeMapper.placeDAOToPlaceInfoDto(updatedPlace));
    }

    @PutMapping("/up/{id}")
    public PlaceInfoDTO upPlace(@RequestBody @Valid PlaceUpdateDTO place, @PathVariable UUID id) {
        log.debug("Update place {}: {}", id, place);
        PlaceDAO updatedPlace = placeService.upPlace(id, placeMapper.placeUpdateDtoToPlaceDAO(place));
        return log.traceExit(placeMapper.placeDAOToPlaceInfoDto(updatedPlace));
    }

    @DeleteMapping("{id}")
    public void deletePlace(@PathVariable UUID id) {
        log.debug("Deleting place {}", id);
        placeService.delete(id);
    }
}
