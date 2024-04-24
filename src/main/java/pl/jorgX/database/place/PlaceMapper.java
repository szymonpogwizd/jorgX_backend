package pl.jorgX.database.place;

import org.mapstruct.Mapper;
import pl.jorgX.database.city.CityMapper;

@Mapper(
        componentModel = "spring",
        uses = {CityMapper.class},
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE
)
public interface PlaceMapper {

    PlaceInfoDTO placeDAOToPlaceInfoDto(PlaceDAO placeDAO);
    PlaceDAO placeCreateDtoToPlaceDAO(PlaceCreateDTO placeCreateDTO);
    PlaceDAO placeUpdateDtoToPlaceDAO(PlaceUpdateDTO placeUpdateDTO);
}
