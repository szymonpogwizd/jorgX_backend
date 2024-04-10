package pl.jorgX.database.city;

import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE
)
public interface CityMapper {

    CityInfoDTO cityDAOToCityInfoDto(CityDAO cityDAO);
    CityDAO cityCreateDtoToCityDAO(CityCreateDTO cityCreateDTO);
    CityDAO cityUpdateDtoToCityDAO(CityUpdateDTO cityUpdateDTO);
}
