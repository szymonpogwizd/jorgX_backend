package pl.jorgX.database.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.jorgX.utils.password.EncodedMapping;
import pl.jorgX.utils.password.PasswordEncoderMapper;

@Mapper(
        componentModel = "spring",
        uses = PasswordEncoderMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    UserInfoDTO userDAO2UserInfoDTO(UserDAO userDAO);

    @Mapping(target = "password", qualifiedBy = EncodedMapping.class)
    UserDAO userCreateDTO2UserDAO(UserCreateDTO userCreateDTO);

    @Mapping(target = "password", qualifiedBy = EncodedMapping.class)
    UserDAO userUpdateDTO2UserDAO(UserUpdateDTO userUpdateDTO);
}
