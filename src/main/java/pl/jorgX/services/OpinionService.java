package pl.jorgX.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jorgX.database.opinion.OpinionDAO;
import pl.jorgX.database.opinion.OpinionRepository;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class OpinionService {

    private final OpinionRepository opinionRepository;
    private final AIModelService aiModelService;

    @Transactional
    public OpinionDAO createOpinion(OpinionDAO opinion) {
        log.debug("Creating opinion: " + opinion);
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

        toUpdate.setOpinion(opinion.getOpinion());
        toUpdate.setUser(opinion.getUser());
        toUpdate.setPlace(opinion.getPlace());

        return log.traceExit(opinionRepository.save(toUpdate));
    }

    public void delete(UUID id) {
        log.debug("Deleting opinion {}", id);
        opinionRepository.deleteById(id);
    }
}
