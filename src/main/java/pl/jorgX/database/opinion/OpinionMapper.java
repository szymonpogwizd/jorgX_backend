package pl.jorgX.database.opinion;

import org.mapstruct.Mapper;
import pl.jorgX.database.place.PlaceMapper;
import pl.jorgX.database.user.UserMapper;

@Mapper(
        componentModel = "spring",
        uses = {PlaceMapper.class, UserMapper.class},
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE
)
public interface OpinionMapper {

    OpinionInfoDTO opinionDAOToOpinionInfoDto(OpinionDAO opinionDAO);
    OpinionDAO opinionCreateDtoToOpinionDAO(OpinionCreateDTO opinionCreateDTO);
    OpinionDAO opinionUpdateDtoToOpinionDAO(OpinionUpdateDTO opinionUpdateDTO);
}
