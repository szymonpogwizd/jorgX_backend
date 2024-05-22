package pl.jorgX.validator;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jorgX.database.opinion.OpinionDAO;
import pl.jorgX.database.opinion.OpinionRepository;
import pl.jorgX.database.place.PlaceDAO;
import pl.jorgX.database.user.UserDAO;
import pl.jorgX.validator.opinion.OpinionValidator;
import pl.jorgX.validator.opinion.OpinionValidatorException;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class OpinionValid {

    private final OpinionRepository opinionRepository;

    public void validateOpinion(OpinionDAO opinionDAO, boolean isSameUser, boolean isupdate) {
        List<String> validationErrors = new ArrayList<>();

        if (opinionDAO.getOpinion() == null || opinionDAO.getOpinion().isEmpty()) {
            validationErrors.add("Opinia nie może być pusta");
        }

//        if (!isSameUser && !isupdate) {
//
//            if (opinionRepository.findByUserIdAndPlaceId(opinionDAO.getUser().getId(), opinionDAO.getPlace().getId()).isPresent()) {
//                validationErrors.add("Opinia tego użytkownika już istnieje");
//            }
//
//            try {
//                OpinionValidator.validate(opinionDAO.getOpinion());
//            } catch (OpinionValidatorException e) {
//                validationErrors.add(e.getMessage());
//            }
//
//            if (!validationErrors.isEmpty()) {
//                String error = String.join("", validationErrors);
//                throw new ValidationException(error);
//            }
//        }
    }


    public boolean checkIfSameOpinion(OpinionDAO opinionDAO) {
        Optional<OpinionDAO> existingOpinion = opinionRepository.findByUserIdAndPlaceId(opinionDAO.getUser().getId(), opinionDAO.getPlace().getId());

        return existingOpinion.isPresent() && existingOpinion.get().equals(opinionDAO);
    }
}
