package pl.jorgX.database.user;

import lombok.Data;
import pl.jorgX.validator.email.Email;
import pl.jorgX.validator.password.Password;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserUpdateDTO {

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank
    private UserType userType;

    @Email
    private String email;

    @Password
    @NotBlank
    private String password;

    private Boolean active;
}
