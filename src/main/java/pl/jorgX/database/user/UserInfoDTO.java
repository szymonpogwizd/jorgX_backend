package pl.jorgX.database.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserInfoDTO {

    private UUID id;

    private String name;

    private UserType userType;

    private String email;

    private Boolean active;
}
