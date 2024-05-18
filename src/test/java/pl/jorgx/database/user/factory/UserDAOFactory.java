package pl.jorgx.database.user.factory;

import pl.jorgX.database.user.UserDAO;
import pl.jorgX.database.user.UserType;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class UserDAOFactory {

    public static final String NAME = "Test User";
    public static final UserType USER_TYPE = UserType.USER;
    public static final String EMAIL = "test@example.com";
    public static final String PASSWORD = "Password1@";
    public static final Boolean ACTIVE = true;
    public static final String TOKEN = UUID.randomUUID().toString();
    public static final ZonedDateTime EXPIRATION = ZonedDateTime.now().plusDays(1);

    public static UserDAO.UserDAOBuilder defaultBuilder() {
        return UserDAO.builder()
                .name(NAME)
                .userType(USER_TYPE)
                .email(EMAIL)
                .password(PASSWORD)
                .active(ACTIVE)
                .token(TOKEN)
                .tokenExpiration(EXPIRATION);
    }

    public static List<UserDAO> defaultList() {
        return List.of(
                defaultBuilder().email("test1@example.com").build(),
                defaultBuilder().email("test2@example.com").build()
        );
    }
}
