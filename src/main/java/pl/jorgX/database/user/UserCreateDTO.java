package pl.jorgX.database.user;

import lombok.Data;
import pl.jorgX.validator.email.Email;
import pl.jorgX.validator.password.Password;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserCreateDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    private UserType userType;

    @Email
    @NotBlank
    private String email;

    @Password
    @NotBlank
    private String password;

    private Boolean active;
}
