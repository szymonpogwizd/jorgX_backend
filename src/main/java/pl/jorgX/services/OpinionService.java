package pl.jorgX.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jorgX.database.opinion.OpinionDAO;
import pl.jorgX.database.opinion.OpinionRepository;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class OpinionService {

    private final OpinionRepository opinionRepository;

    @Transactional
    public OpinionDAO createOpinion(OpinionDAO opinion) {
        log.debug("Creating opinion: " + opinion);
        return log.traceExit(opinionRepository.save(opinion));
    }

    @Transactional
    public List<OpinionDAO> getOpinionByPlaceId(UUID id) {
        log.debug("Getting opinion by places id: " + id);
        return log.traceExit(opinionRepository.findByPlaceId(id));
    }
}
