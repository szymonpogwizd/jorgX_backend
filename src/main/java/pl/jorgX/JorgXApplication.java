package pl.jorgX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JorgXApplication {

    public static void main(String[] args) {
        SpringApplication.run(JorgXApplication.class, args);
    }
}

/*
 TODO do usunięcia po zakończeniu implementacji modelu - kod dla testów
public class JorgXApplication {

    public static void main(String[] args) {
        ModelAPIConnector modelClient = new ModelAPIConnector("http://localhost:5000/predict");
        String result = modelClient.makeRequest("Nie polecam");
        System.out.println("Received result: " + result);
    }
}
*/
