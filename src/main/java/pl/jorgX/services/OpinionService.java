package pl.jorgX.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jorgX.database.opinion.OpinionDAO;
import pl.jorgX.database.opinion.OpinionRepository;
import pl.jorgX.database.place.PlaceDAO;
import pl.jorgX.database.user.UserDAO;
import pl.jorgX.validator.OpinionValid;
import pl.jorgX.validator.opinion.OpinionValidator;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class OpinionService {

    private final OpinionRepository opinionRepository;
    private final OpinionValid opinionValidator;
    private final AIModelService aiModelService;

    @Transactional
    public OpinionDAO createOpinion(OpinionDAO opinion) {
        log.debug("Creating opinion: " + opinion);
        opinionValidator.validateOpinion(opinion, false);
        opinion.setOpinionType(aiModelService.getOpinionType(opinion.getOpinion()));
        return log.traceExit(opinionRepository.save(opinion));
    }

    @Transactional
    public List<OpinionDAO> getOpinionByPlaceId(UUID id) {
        log.debug("Getting opinion by places id: " + id);
        return log.traceExit(opinionRepository.findByPlaceId(id));
    }

    @Transactional
    public OpinionDAO update(UUID id, OpinionDAO opinion) {
        log.debug("Editing opinion {} - {}", id, opinion);
        OpinionDAO toUpdate = opinionRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Opinion with id " + id + " was not found"));
        toUpdate.setPlace(opinion.getPlace());
        toUpdate.setOpinion(opinion.getOpinion());
        toUpdate.setUser(opinion.getUser());
        boolean isSameOpinion = opinionValidator.checkIfSameOpinion(opinion);
        opinionValidator.validateOpinion(opinion, isSameOpinion);
        return log.traceExit(opinionRepository.save(toUpdate));
    }

    public Optional<OpinionDAO> findByUserAndPlace(UserDAO userID, PlaceDAO placeID)
    {
        log.debug("Getting opinion by user: " + userID + " and place: "+ placeID);
        return log.traceExit(opinionRepository.findByUserAndPlace(userID, placeID));
    }

    public void delete(UUID id) {
        log.debug("Deleting opinion {}", id);
        opinionRepository.deleteById(id);
    }
}
