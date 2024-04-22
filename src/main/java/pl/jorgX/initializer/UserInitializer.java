package pl.jorgX.initializer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jorgX.database.user.UserRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserInitializer {

    private final UserRepository userRepository;

    public void initialize() {
        // hasło: qwQW12!@12

        userRepository.insertUser(
                UUID.fromString("1b7c849c-a3e5-4b9d-a868-3c64416f04bf"),
                true,
                "admin@jorgx.pl",
                "Admin",
                "$2a$10$ZavydPLj7og39pWGFIKc0erCzLgGdxontrcwQ5c492ZcpcjwZa2W6",
                "ADMINISTRATOR",
                true
        );

        userRepository.insertUser(
                UUID.fromString("feaf1c67-7241-4086-8f1c-a7a60b05af84"),
                true,
                "user@jorgx.pl",
                "User",
                "$2a$10$ZavydPLj7og39pWGFIKc0erCzLgGdxontrcwQ5c492ZcpcjwZa2W6",
                "USER",
                true
        );
    }
}
