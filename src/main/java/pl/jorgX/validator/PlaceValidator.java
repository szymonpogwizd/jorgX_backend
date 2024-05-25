package pl.jorgX.validator;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jorgX.database.place.PlaceDAO;
import pl.jorgX.database.place.PlaceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PlaceValidator {

    private final PlaceRepository placeRepository;

    public boolean checkIfSamePlace(UUID id, PlaceDAO placeDAO) {
        Optional<PlaceDAO> optionalPlaceDAO1 = placeRepository.findByName(placeDAO.getStreet());
        Optional<PlaceDAO> placeDAO1 = placeRepository.findByName(placeDAO.getName());
        boolean isSamePlaceByStreet = optionalPlaceDAO1.isPresent() && optionalPlaceDAO1.get().getId().equals(id);
        boolean isSamePlaceByName = placeDAO1.isPresent() && placeDAO1.get().getId().equals(id);
        return isSamePlaceByStreet && isSamePlaceByName;
    }

    public void validatePlace(PlaceDAO placeDAO, boolean isSamePlace) {
        List<String> validationErrors = new ArrayList<>();

        if (placeDAO.getName() == null || placeDAO.getName().isEmpty()) {
            validationErrors.add("Nazwa miejsca nie może być pusta\n");
        }

        if (!isSamePlace) {

            if (placeRepository.findByNameAndStreet(placeDAO.getName(), placeDAO.getStreet()).isPresent()) {
                validationErrors.add("Miejsce o tym samym adresie i nazwie już istnieje\n");
            }
        }

        if (!validationErrors.isEmpty()) {
            String errorMessage = String.join("", validationErrors);
            throw new ValidationException(errorMessage);
        }
    }
}
