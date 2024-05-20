package pl.jorgx.database.user;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.jorgX.database.user.*;
import pl.jorgx.database.configuration.MapperConfiguration;
import pl.jorgx.database.user.factory.UserDAOFactory;
import pl.jorgx.database.user.factory.UserDTOFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MapperConfiguration.class)
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void userDAO2UserInfoDTO() {
        // given
        UserDAO userDAO = UserDAOFactory.defaultBuilder().build();

        // when
        UserInfoDTO userInfoDTO = userMapper.userDAO2UserInfoDTO(userDAO);

        // then
        assertNotNull(userInfoDTO);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(userDAO.getName()).isEqualTo(userInfoDTO.getName());
        softly.assertThat(userDAO.getUserType()).isEqualTo(userInfoDTO.getUserType());
        softly.assertThat(userDAO.getEmail()).isEqualTo(userInfoDTO.getEmail());
        softly.assertThat(userDAO.getActive()).isEqualTo(userInfoDTO.getActive());
        softly.assertAll();
    }

    @Test
    void userCreateDTO2UserDAO() {
        //given
        UserCreateDTO userCreateDTO = UserDTOFactory.defaultUserCreateDTO();

        //when
        UserDAO userDAO = userMapper.userCreateDTO2UserDAO(userCreateDTO);

        //then
        assertNotNull(userDAO);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(userCreateDTO.getActive()).isEqualTo(userDAO.getActive());
        softly.assertThat(userCreateDTO.getUserType()).isEqualTo(userDAO.getUserType());
        softly.assertThat(userCreateDTO.getEmail()).isEqualTo(userDAO.getEmail());
        softly.assertThat(userCreateDTO.getName()).isEqualTo(userDAO.getName());
        softly.assertThat(passwordEncoder.matches(userCreateDTO.getPassword(), userDAO.getPassword())).isTrue();
        softly.assertAll();
    }

    @Test
    void userUpdateDTO2UserDAO() {
        //given
        UserUpdateDTO userUpdateDTO = UserDTOFactory.defaultUserUpdateDTO();

        //when
        UserDAO userDAO = userMapper.userUpdateDTO2UserDAO(userUpdateDTO);

        //then
        assertNotNull(userDAO);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(userUpdateDTO.getActive()).isEqualTo(userDAO.getActive());
        softly.assertThat(userUpdateDTO.getUserType()).isEqualTo(userDAO.getUserType());
        softly.assertThat(userUpdateDTO.getEmail()).isEqualTo(userDAO.getEmail());
        softly.assertThat(userUpdateDTO.getName()).isEqualTo(userDAO.getName());
        softly.assertThat(passwordEncoder.matches(userUpdateDTO.getPassword(), userDAO.getPassword())).isTrue();
        softly.assertAll();
    }
}