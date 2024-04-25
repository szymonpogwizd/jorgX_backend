package pl.jorgX.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jorgX.database.opinion.OpinionDAO;
import pl.jorgX.database.opinion.OpinionType;
import pl.jorgX.database.place.PlaceDAO;
import pl.jorgX.database.place.PlaceRepository;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final OpinionService opinionService;

    @Transactional
    public PlaceDAO createPlace(PlaceDAO opinion) {
        log.debug("Creating opinion: " + opinion);
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

    @Transactional
    public PlaceDAO update(UUID id, PlaceDAO place) {
        log.debug("Editing place {} - {}", id, place);
        PlaceDAO toUpdate = placeRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Place with id " + id + " was not found"));

        toUpdate.setName(place.getName());
        toUpdate.setOpeningHours(place.getOpeningHours());
        toUpdate.setStreet(place.getStreet());
        toUpdate.setCity(place.getCity());

        return log.traceExit(placeRepository.save(toUpdate));
    }

    public void delete(UUID id) {
        log.debug("Deleting place {}", id);
        placeRepository.deleteById(id);
    }

    @Transactional
    public void updateRatingForPlace(UUID placeId) {
        List<OpinionType> opinionTypeList = opinionService.getOpinionByPlaceId(placeId).stream().map(OpinionDAO::getOpinionType).toList();
        double newRating = calculateNewRating(opinionTypeList);
        placeRepository.updatePlaceRating(placeId, newRating);
    }

    private double calculateNewRating(List<OpinionType> opinions) {
        if (opinions.isEmpty()) {
            return 0;
        }
        double sum = opinions.stream()
                .mapToDouble(this::getRatingFromOpinionType)
                .sum();
        double average = sum / opinions.size();
        return Math.round(average * 100.0) / 100.0;
    }


    private double getRatingFromOpinionType(OpinionType opinionType) {
        return switch (opinionType) {
            case POSITIVE -> 5.0;
            case AMBIGUOUS, NEUTRAL -> 3.0;
            case NEGATIVE -> 1.0;
        };
    }
}
