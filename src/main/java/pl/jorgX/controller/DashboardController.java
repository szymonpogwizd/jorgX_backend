package pl.jorgX.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jorgX.services.StatisticsService;

@Log4j2
@RestController
@RequestMapping("/dashboard/app")
@RequiredArgsConstructor
@CrossOrigin
public class DashboardController {

    private final StatisticsService statisticsService;

    @GetMapping("/users")
    public int getNumberOfUsers() {
        log.debug("Getting number of users");
        return log.traceExit(statisticsService.getNumberOfUsers());
    }

    @GetMapping("/cities")
    public int getNumberOfCities() {
        log.debug("Getting number of cities");
        return log.traceExit(statisticsService.getNumberOfCities());
    }

    @GetMapping("/places")
    public int getNumberOfPlaces() {
        log.debug("Getting number of places");
        return log.traceExit(statisticsService.getNumberOfPlaces());
    }

    @GetMapping("/opinions")
    public int getNumberOfOpinions() {
        log.debug("Getting number of opinions");
        return log.traceExit(statisticsService.getNumberOfOpinions());
    }
}
