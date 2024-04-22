package pl.jorgX.initializer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final UserInitializer userInitializer;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        userInitializer.initialize();
    }
}