package pl.jorgX.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.jorgX.database.city.CityRepository;
import pl.jorgX.database.opinion.OpinionRepository;
import pl.jorgX.database.place.PlaceRepository;
import pl.jorgX.database.user.UserRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class StatisticsService {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final PlaceRepository placeRepository;
    private final OpinionRepository opinionRepository;

    public int getNumberOfUsers() {
        log.debug("Getting number of users");
        return log.traceExit(userRepository.countUsers());
    }

    public int getNumberOfCities() {
        log.debug("Getting number of cities");
        return log.traceExit(cityRepository.countCity());
    }

    public int getNumberOfPlaces() {
        log.debug("Getting number of places");
        return log.traceExit(placeRepository.countPlaces());
    }

    public int getNumberOfOpinions() {
        log.debug("Getting number of opinions");
        return log.traceExit(opinionRepository.countOpinions());
    }
}
