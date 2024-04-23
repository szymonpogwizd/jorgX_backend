package pl.jorgX.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import pl.jorgX.database.city.*;
import pl.jorgX.database.user.UserDAO;
import pl.jorgX.database.user.UserInfoDTO;
import pl.jorgX.database.user.UserUpdateDTO;
import pl.jorgX.services.CityService;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/dashboard/city")
@RequiredArgsConstructor
@CrossOrigin
public class CityController {

    private final CityService cityService;
    private final CityMapper cityMapper;

    @PostMapping
    public CityInfoDTO creteCity(@RequestBody @Valid CityCreateDTO city) {
        log.debug("Create city {}", city);
        CityDAO toCreate = cityMapper.cityCreateDtoToCityDAO(city);
        toCreate.setName(city.getName());
        CityDAO createdCity = cityService.createCity(toCreate);
        return log.traceExit(cityMapper.cityDAOToCityInfoDto(createdCity));
    }

    @GetMapping
    public List<CityInfoDTO> getAll() {
        log.debug("Getting all cities");
        return log.traceExit(cityService.getAll()
                .stream()
                .map(cityMapper::cityDAOToCityInfoDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{name}")
    public CityInfoDTO getCityByName(@PathVariable String name) {
        log.debug("Getting city by name: {}", name);
        return log.traceExit(cityService.getCityByName(name)
                .map(cityMapper::cityDAOToCityInfoDto)
                .orElseThrow(() -> new RuntimeException("City not found")));
    }

    @PutMapping("{id}")
    public CityInfoDTO updateUser(@RequestBody @Valid CityUpdateDTO city, @PathVariable UUID id) {
        log.debug("Update user {}: {}", id, city);
        CityDAO updatedCity = cityService.update(id, cityMapper.cityUpdateDtoToCityDAO(city));
        return log.traceExit(cityMapper.cityDAOToCityInfoDto(updatedCity));
    }




    @DeleteMapping("{id}")
    public void deleteCity(@PathVariable UUID id) {
        log.debug("Deleting city {}", id);
        cityService.delete(id);
    }
}
