package pl.jorgX.initializer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jorgX.database.city.CityRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CityInitializer {

    private final CityRepository cityRepository;

    public void initialize() {
        cityRepository.insertCity(UUID.fromString("51539cc2-b8ae-4743-93d6-6446f73e0d7f"), "Warszawa", null);
        cityRepository.insertCity(UUID.fromString("9a363542-a09c-408f-b582-73eb3b0b43c6"), "Kraków", null);
    }
}
