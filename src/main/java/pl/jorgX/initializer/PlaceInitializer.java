package pl.jorgX.initializer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jorgX.database.place.PlaceRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PlaceInitializer {

    private final PlaceRepository placeRepository;

    public void initialize() {
        placeRepository.insertPlace(
                UUID.fromString("20f7c5d7-4212-4e66-a250-ee3ace1d174c"),
                "KFC",
                "10:00-22:00",
                5.0,
                "KFC Street",
                UUID.fromString("9a363542-a09c-408f-b582-73eb3b0b43c6")
        );

        placeRepository.insertPlace(
                UUID.fromString("e73948b7-5122-497d-8c09-972bbe021f7a"),
                "McDonald",
                "10:00-22:00",
                3.0,
                "McDonald Street",
                UUID.fromString("9a363542-a09c-408f-b582-73eb3b0b43c6")
        );

        placeRepository.insertPlace(
                UUID.fromString("1755e8bb-95f3-4da5-abbd-8b0e7fc49c8e"),
                "Burger King",
                "10:00-22:00",
                2.0,
                "Burger King Street",
                UUID.fromString("9a363542-a09c-408f-b582-73eb3b0b43c6")
        );

        placeRepository.insertPlace(
                UUID.fromString("6e2a20e3-0237-4c6a-acfb-80dc2ab51b73"),
                "Subway",
                "10:00-22:00",
                3.0,
                "Subway Street",
                UUID.fromString("9a363542-a09c-408f-b582-73eb3b0b43c6")
        );

        placeRepository.insertPlace(
                UUID.fromString("61a4525f-7e41-4f10-ab28-7a8a56a314c8"),
                "Pizza Hut",
                "10:00-22:00",
                4.0,
                "Pizza Hut Street",
                UUID.fromString("51539cc2-b8ae-4743-93d6-6446f73e0d7f")
        );

        placeRepository.insertPlace(
                UUID.fromString("cd64dd50-3f51-420e-8f25-376ee91a2a4d"),
                "Starbucks",
                "10:00-22:00",
                 1.0,
                "Starbucks Street",
                UUID.fromString("51539cc2-b8ae-4743-93d6-6446f73e0d7f")
        );
    }
}
