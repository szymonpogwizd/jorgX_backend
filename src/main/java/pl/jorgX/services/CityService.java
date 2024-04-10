package pl.jorgX.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jorgX.database.city.CityDAO;
import pl.jorgX.database.city.CityRepository;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    @Transactional
    public CityDAO createCity(CityDAO city) {
        log.debug("Creating city: " + city);
        return log.traceExit(cityRepository.save(city));
    }

    public List<CityDAO> getAll() {
        log.debug("Getting all cities");
        return log.traceExit(cityRepository.findAll());
    }

    public Optional<CityDAO> getCityByName(String name) {
        log.debug("Getting city by name: " + name);
        return log.traceExit(cityRepository.findByName(name));
    }
}
