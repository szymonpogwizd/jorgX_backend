package pl.jorgX.initializer;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jorgX.database.city.cityRepository;
import pl.jorgX.database.opinion.opinionRepository;
import pl.jorgX.database.place.placeRepository;

@Component
public class databaseInitializer {

    @Autowired
    private cityRepository cityRepository;

    @Autowired
    private placeRepository placeRepository;

    @Autowired
    private opinionRepository opinionRepository;

    @PostConstruct
    public void init() {

    }

}
