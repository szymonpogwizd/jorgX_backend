package pl.jorgX.database.opinion;

import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE
)
public interface OpinionMapper {

    OpinionInfoDTO opinionDAOToOpinionInfoDto(OpinionDAO opinionDAO);
    OpinionDAO opinionCreateDtoToOpinionDAO(OpinionCreateDTO opinionCreateDTO);
    OpinionDAO opinionUpdateDtoToOpinionDAO(OpinionUpdateDTO opinionUpdateDTO);
}
