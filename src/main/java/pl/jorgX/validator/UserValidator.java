package pl.jorgX.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jorgX.database.user.UserDAO;
import pl.jorgX.database.user.UserRepository;
import pl.jorgX.database.user.UserType;
import pl.jorgX.validator.email.EmailValidator;
import pl.jorgX.validator.email.EmailValidatorException;
import pl.jorgX.validator.password.PasswordValidator;
import pl.jorgX.validator.password.PasswordValidatorException;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void validateUser(UserDAO user, boolean isSameUser) {
        List<String> validationErrors = new ArrayList<>();

        if (user.getName() == null || user.getName().isEmpty()) {
            validationErrors.add("Nazwa użytkownika nie może być pusta\n");
        }

        if (!isSameUser) {
            if (userRepository.findByName(user.getName()).isPresent()) {
                validationErrors.add("Użytkownik o podanej nazwie już istnieje\n");
            }

            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                validationErrors.add("Użytkownik o podanym adresie e-mail już istnieje\n");
            }
        }

        try {
            EmailValidator.validate(user.getEmail());
        } catch (EmailValidatorException e) {
            validationErrors.add(e.getMessage() + "\n");
        }

        try {
            PasswordValidator.validate(user.getPassword());
        } catch (PasswordValidatorException e) {
            validationErrors.add(e.getMessage() + "\n");
        }

        if (!validationErrors.isEmpty()) {
            String errorMessage = String.join("", validationErrors);
            throw new ValidationException(errorMessage);
        }
    }

    public boolean checkIfSameUser(UUID id, UserDAO user) {
        Optional<UserDAO> foundUserByName = userRepository.findByName(user.getName());
        Optional<UserDAO> foundUserByEmail = userRepository.findByEmail(user.getEmail());
        boolean isSameUserByName = foundUserByName.isPresent() && foundUserByName.get().getId().equals(id);
        boolean isSameUserByEmail = foundUserByEmail.isPresent() && foundUserByEmail.get().getId().equals(id);
        return isSameUserByName && isSameUserByEmail;
    }

    public void parseUserType(UserDAO user) {
        UserType userType = UserType.valueOf(String.valueOf(user.getUserType()));

        if (userType == UserType.USER) {
            user.setUserType(UserType.USER);
        } else if (userType == UserType.ADMINISTRATOR) {
            user.setUserType(UserType.ADMINISTRATOR);
        }
    }
}
