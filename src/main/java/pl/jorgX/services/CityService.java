package pl.jorgX.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jorgX.database.city.CityCreateDTO;
import pl.jorgX.database.city.CityDAO;
import pl.jorgX.database.city.CityMapper;
import pl.jorgX.database.city.CityRepository;
import pl.jorgX.validator.CityValidator;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final CityValidator cityValidator;

    @Transactional
    public CityDAO createCity(CityDAO city) {
        log.debug("Creating city: " + city);
//        cityValidator.ValidateCity(city,false);
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

    @Transactional
    public CityDAO update(UUID id, CityDAO city) {
        log.debug("Editing city {} - {}", id, city);
//        boolean isSameCity = cityValidator.checkSameCity(id,city);
//        cityValidator.ValidateCity(city,isSameCity);
        CityDAO toUpdate = cityRepository.findById(id)
                .orElseThrow(() -> new ValidationException("City with id " + id + " was not found"));

        toUpdate.setName(city.getName());

        return log.traceExit(cityRepository.save(toUpdate));
    }

    public void delete(UUID id) {
        log.debug("Deleting city {}", id);
        cityRepository.deleteById(id);
    }

    @Transactional
    public UUID getOrCreateCityId(CityCreateDTO cityCreateDTO) {
        return cityRepository.findByName(cityCreateDTO.getName())
                .map(CityDAO::getId)
                .orElseGet(() -> {
                    CityDAO newCity = cityMapper.cityCreateDtoToCityDAO(cityCreateDTO);
                    newCity.setName(cityCreateDTO.getName());
                    cityRepository.save(newCity);
                    return newCity.getId();
                });
    }
}
