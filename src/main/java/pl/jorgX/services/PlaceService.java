package pl.jorgX.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jorgX.database.city.CityDAO;
import pl.jorgX.database.opinion.OpinionDAO;
import pl.jorgX.database.opinion.OpinionType;
import pl.jorgX.database.place.PlaceDAO;
import pl.jorgX.database.place.PlaceRepository;
import pl.jorgX.validator.PlaceValidator;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final OpinionService opinionService;
    private final PlaceValidator placeValidator;

    @Transactional
    public PlaceDAO createPlace(PlaceDAO opinion) {
        log.debug("Creating opinion: " + opinion);
//        placeValidator.validatePlace(opinion,false);
        return log.traceExit(placeRepository.save(opinion));
    }

    public List<PlaceDAO> getAll() {
        log.debug("Getting all opinions");
        return log.traceExit(placeRepository.findAll());
    }

    public Optional<PlaceDAO> getPlaceByName(String name) {
        log.debug("Getting place by name: " + name);
        return log.traceExit(placeRepository.findByName(name));
    }

    public List<PlaceDAO> getPlaceByCityId(UUID id) {
        log.debug("Getting place by city: " + id);
        return log.traceExit(placeRepository.findByCityId(id));
    }

    public Optional<PlaceDAO> getPlaceByOpinionId(UUID id) {
        log.debug("Getting place by opinion id: " + id);
        return log.traceExit(placeRepository.findByOpinionId(id));
    }

    public Optional<PlaceDAO> findByNameAndStreet(String street, String name) {
        log.debug("Getting place by street: {} and name: {}", street, name);
        return placeRepository.findByNameAndStreet(name, street);
    }

    public boolean placeExists(String street, String name) {
        log.debug("Checking if place exists by street: {} and name: {}", street, name);
        return placeRepository.findByNameAndStreet(name, street).isPresent();
    }


    @Transactional
    public PlaceDAO update(UUID id, PlaceDAO place) {
        log.debug("Editing place {} - {}", id, place);
        System.out.println("Id: " + place);
        boolean isSamePlace = placeValidator.checkIfSamePlace(id,place);
//        placeValidator.validatePlace(place,isSamePlace);
        PlaceDAO toUpdate = placeRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Place with id " + id + " was not found"));
        toUpdate.setName(place.getName());
        toUpdate.setOpeningHours(place.getOpeningHours());
        toUpdate.setStreet(place.getStreet());
        return log.traceExit(placeRepository.save(toUpdate));
    }

    public void delete(UUID id) {
        log.debug("Deleting place {}", id);
        placeRepository.deleteById(id);
    }

    @Transactional
    public void updateRatingForPlace(UUID placeId) {
        List<OpinionType> opinionTypeList = opinionService.getOpinionByPlaceId(placeId).stream().map(OpinionDAO::getOpinionType).toList();
        Double newRating = calculateNewRating(opinionTypeList);
        placeRepository.updatePlaceRating(placeId, newRating);
    }

    private Double calculateNewRating(List<OpinionType> opinions) {
        List<Double> ratings = opinions.stream()
                .map(this::getRatingFromOpinionType)
                .filter(Objects::nonNull)
                .toList();

        if (ratings.isEmpty()) {
            return null;
        }

        double sum = ratings.stream().mapToDouble(Double::doubleValue).sum();
        double average = sum / ratings.size();
        return Math.round(average * 100.0) / 100.0;
    }


    private Double getRatingFromOpinionType(OpinionType opinionType) {
        return switch (opinionType) {
            case POSITIVE -> 5.0;
            case AMBIGUOUS -> 3.0;
            case NEGATIVE -> 1.0;
            case NEUTRAL -> null;
        };
    }
}
