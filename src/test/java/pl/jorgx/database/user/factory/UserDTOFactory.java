package pl.jorgx.database.user.factory;

import pl.jorgX.database.user.UserCreateDTO;
import pl.jorgX.database.user.UserUpdateDTO;

import static pl.jorgx.database.user.factory.UserDAOFactory.PASSWORD;

public class UserDTOFactory {

    public static UserCreateDTO defaultUserCreateDTO() {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setActive(UserDAOFactory.ACTIVE);
        userCreateDTO.setUserType(UserDAOFactory.USER_TYPE);
        userCreateDTO.setEmail(UserDAOFactory.EMAIL);
        userCreateDTO.setName(UserDAOFactory.NAME);
        userCreateDTO.setPassword(PASSWORD);

        return userCreateDTO;
    }

    public static UserUpdateDTO defaultUserUpdateDTO() {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setActive(UserDAOFactory.ACTIVE);
        userUpdateDTO.setUserType(UserDAOFactory.USER_TYPE);
        userUpdateDTO.setName(UserDAOFactory.NAME);
        userUpdateDTO.setEmail(UserDAOFactory.EMAIL);

        return userUpdateDTO;
    }
}
