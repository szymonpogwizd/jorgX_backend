package pl.jorgX.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jorgX.database.opinion.*;
import pl.jorgX.database.place.PlaceDAO;
import pl.jorgX.database.place.PlaceRepository;
import pl.jorgX.database.user.UserRepository;
import pl.jorgX.services.OpinionService;
import pl.jorgX.services.PlaceService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/dashboard/opinion")
@RequiredArgsConstructor
@CrossOrigin
public class OpinionController {

    private final OpinionService opinionService;
    private final PlaceService placeService;
    private final OpinionMapper opinionMapper;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    @PostMapping
    public OpinionInfoDTO createOpinion(@RequestBody @Valid OpinionCreateDTO opinion) {
        log.debug("Create opinion {}", opinion);
        OpinionDAO toCreate = opinionMapper.opinionCreateDtoToOpinionDAO(opinion);
        placeRepository.findById(opinion.getPlaceId()).ifPresent(toCreate::setPlace);
        userRepository.findByEmail(opinion.getEmail()).ifPresent(toCreate::setUser);
        toCreate.setOpinion(opinion.getOpinion());
        OpinionDAO createdOpinion = opinionService.createOpinion(toCreate);
        placeService.updateRatingForPlace(createdOpinion.getPlace().getId());
        return log.traceExit(opinionMapper.opinionDAOToOpinionInfoDto(createdOpinion));
    }

    @GetMapping("/place/{id}")
    public List<OpinionInfoDTO> getOpinionByPlaceId(@PathVariable UUID id) {
        log.debug("Getting opinion by place id: {}", id);
        return log.traceExit(opinionService.getOpinionByPlaceId(id)
                .stream()
                .map(opinionMapper::opinionDAOToOpinionInfoDto)
                .collect(Collectors.toList()));
    }

    @PutMapping("{id}")
    public OpinionInfoDTO updateOpinion(@RequestBody @Valid OpinionUpdateDTO opinion, @PathVariable UUID id) {
        log.debug("Update opinion {}: {}", id, opinion);
        OpinionDAO updatedOpinion = opinionService.update(id, opinionMapper.opinionUpdateDtoToOpinionDAO(opinion));
        return log.traceExit(opinionMapper.opinionDAOToOpinionInfoDto(updatedOpinion));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOpinion(@PathVariable UUID id) {
        log.debug("Deleting opinion {}", id);
        Optional<PlaceDAO> placeDAO = placeService.getPlaceByOpinionId(id);

        if (placeDAO.isPresent()) {
            opinionService.delete(id);
            placeService.updateRatingForPlace(placeDAO.get().getId());
            return ResponseEntity.ok("Opinion deleted and place rating updated");
        } else {
            log.error("Place for opinion {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Place not found for the given opinion ID");
        }
    }
}
