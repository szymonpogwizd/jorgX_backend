package pl.jorgX.validator.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=|,.<>?]).{8,}$");

    public static void validate(String password) {
        if (password == null || password.isBlank()) {
            throw new PasswordValidatorException("Hasło nie może być puste");
        }
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new PasswordValidatorException("Podane hasło nie spełnia wymagań: musi zawierać co najmniej 8 znaków, jedną cyfrę, jedną małą literę, jedną dużą literę oraz jeden znak specjalny.");
        }
    }

    @Override
    public void initialize(Password constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.isBlank() && PASSWORD_PATTERN.matcher(value).matches();
    }
}
