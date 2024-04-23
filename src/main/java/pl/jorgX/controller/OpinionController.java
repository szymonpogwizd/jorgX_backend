package pl.jorgX.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import pl.jorgX.database.city.CityDAO;
import pl.jorgX.database.city.CityInfoDTO;
import pl.jorgX.database.city.CityUpdateDTO;
import pl.jorgX.database.opinion.*;
import pl.jorgX.database.place.PlaceRepository;
import pl.jorgX.services.OpinionService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/dashboard/opinion")
@RequiredArgsConstructor
@CrossOrigin
public class OpinionController {

    private final OpinionService opinionService;
    private final OpinionMapper opinionMapper;
    private final PlaceRepository placeRepository;

    @PostMapping
    public OpinionInfoDTO createOpinion(@RequestBody @Valid OpinionCreateDTO opinion) {
        log.debug("Create opinion {}", opinion);
        OpinionDAO toCreate = opinionMapper.opinionCreateDtoToOpinionDAO(opinion);
        placeRepository.findById(opinion.getPlaceId()).ifPresent(toCreate::setPlace);
        toCreate.setOpinion(opinion.getOpinion());
        toCreate.setNick(opinion.getNick());
        OpinionDAO createdOpinion = opinionService.createOpinion(toCreate);
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
    public OpinionInfoDTO updateOpinion(@RequestBody @Valid OpinionUpdateDTO opinion, @PathVariable UUID id)
    {
        log.debug("Update opinion {}: {}",id, opinion);
        OpinionDAO updatedOpinionDAO = opinionService.update(id, opinionMapper.opinionUpdateDtoToOpinionDAO(opinion));
        OpinionInfoDTO updatedOpinionDTO = opinionMapper.opinionDAOToOpinionInfoDto(updatedOpinionDAO);
        return log.traceExit(updatedOpinionDTO);
    }

    @DeleteMapping("{id}")
    public void deleteOpinion(@PathVariable UUID id) {
        log.debug("Deleting city {}", id);
        opinionService.delete(id);
    }
}
