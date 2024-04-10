package pl.jorgX.database.place;

import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE
)
public interface PlaceMapper {

    PlaceInfoDTO placeDAOToPlaceInfoDto(PlaceDAO placeDAO);
    PlaceDAO placeCreateDtoToPlaceDAO(PlaceCreateDTO placeCreateDTO);
    PlaceDAO placeUpdateDtoToPlaceDAO(PlaceUpdateDTO placeUpdateDTO);
}
