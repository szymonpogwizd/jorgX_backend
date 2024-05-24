package pl.jorgX.validator;

import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jorgX.database.city.CityDAO;
import pl.jorgX.database.city.CityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CityValidator {

    private final CityRepository repository;

    public void ValidateCity(CityDAO cityDAO, boolean isSameCity) {
        List<String> validationErrors = new ArrayList<>();

        if (cityDAO.getName() == null || cityDAO.getName().isEmpty()) {
            validationErrors.add("Nazwa miasta nie może być pusta");
        }

        if (!validationErrors.isEmpty()) {
            String error = String.join("", validationErrors);
            try {
                throw new ValidationException(error);
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean checkSameCity(UUID uuid, CityDAO cityDAO) {
        Optional<CityDAO> cityDAO1 = repository.findByName(cityDAO.getName());

        boolean isSameCity = cityDAO1.isPresent() && cityDAO1.get().getId().equals(uuid);

        return isSameCity;
    }
}
