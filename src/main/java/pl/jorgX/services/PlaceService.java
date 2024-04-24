package pl.jorgX.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jorgX.database.city.CityDAO;
import pl.jorgX.database.city.CityRepository;
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
    private final CityRepository cityRepository;

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

    public void delete(UUID id) {
        log.debug("Deleting place {}", id);
        placeRepository.deleteById(id);
    }

    @Transactional
    public PlaceDAO update(UUID id, PlaceDAO place)
    {
        log.debug("Editing place {} - {}",id,place);
        PlaceDAO toUpdate = placeRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Place with id " + id + " was not found"));
        if (place != null) {
            toUpdate.setName(place.getName());
            toUpdate.setOpeningHours(place.getOpeningHours());
            toUpdate.setStreet(place.getStreet());

            CityDAO city = place.getCity();

            if (city != null) {
                toUpdate.setCity(city);
            }
        }
        return log.traceExit(placeRepository.save(toUpdate));
    }
}
