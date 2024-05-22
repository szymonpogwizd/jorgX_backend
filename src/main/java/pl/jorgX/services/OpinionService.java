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

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class OpinionService {

    private final OpinionRepository opinionRepository;
    private final AIModelService aiModelService;
    private final OpinionValid opinionValid;

    @Transactional
    public OpinionDAO createOpinion(OpinionDAO opinion) {
        log.debug("Creating opinion: " + opinion);
        opinionValid.validateOpinion(opinion,false,false);
        opinion.setOpinionType(aiModelService.getOpinionType(opinion.getOpinion()));
        return log.traceExit(opinionRepository.save(opinion));
    }

    @Transactional
    public List<OpinionDAO> getOpinionByPlaceId(UUID id) {
        log.debug("Getting opinion by places id: " + id);
        return log.traceExit(opinionRepository.findByPlaceId(id));
    }

    public Optional<OpinionDAO> findByUserAndPlace(UUID userID, UUID placeID) {
        log.debug("Getting opinion by user: {} and place: {}", userID, placeID);
        return log.traceExit(opinionRepository.findByUserIdAndPlaceId(userID, placeID));
    }

    @Transactional
    public OpinionDAO update(UUID id, OpinionDAO opinion) {
        log.debug("Editing opinion {} - {}", id, opinion);

        System.out.println("Id: " + opinion);

       boolean isSameOpinion = opinionValid.checkIfSameOpinion(opinion);
       opinionValid.validateOpinion(opinion,isSameOpinion,true);
        OpinionDAO toUpdate = opinionRepository.findById(id)
               .orElseThrow(() -> new ValidationException("Opinion with id " + id + " was not found"));
        toUpdate.setOpinion(opinion.getOpinion());
        toUpdate.setOpinionType(aiModelService.getOpinionType(opinion.getOpinion()));
        return log.traceExit(opinionRepository.save(toUpdate));
    }

    public void deleteOpinionsByPlaceId(UUID placeId) {
        opinionRepository.deleteAllWithPlaceId(placeId);
    }

    public void delete(UUID id) {
        log.debug("Deleting opinion {}", id);
        opinionRepository.deleteById(id);
    }
}
