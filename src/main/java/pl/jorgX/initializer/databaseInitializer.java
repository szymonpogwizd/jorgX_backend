package pl.jorgX.initializer;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jorgX.database.city.CityRepository;
import pl.jorgX.database.opinion.OpinionRepository;
import pl.jorgX.database.place.PlaceRepository;

@Component
public class databaseInitializer {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private OpinionRepository opinionRepository;

    @PostConstruct
    public void init() {

    }

}
