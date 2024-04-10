package pl.jorgX.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jorgX.database.place.PlaceDAO;
import pl.jorgX.database.place.PlaceRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

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
}
