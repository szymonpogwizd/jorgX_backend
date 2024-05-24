package pl.jorgX.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.jorgX.ModelAPIConnector;
import pl.jorgX.database.opinion.OpinionType;

import java.io.IOException;

@Service
@Log4j2
@RequiredArgsConstructor
public class AIModelService {

    private final ModelAPIConnector modelClient = new ModelAPIConnector("http://localhost:5000/predict");

    public OpinionType getOpinionType(String opinion) {
        log.debug("Getting opinion type for opinion: {}", opinion);
        try {
            opinion = encodeJsonString(opinion);
            String response = modelClient.makeRequest(opinion);
            double[] predictions = parsePredictions(response);
            return determineOpinionType(predictions);
        } catch (IOException e) {
            log.error("Error parsing predictions: ", e);
            return null;
        }
    }

    private String encodeJsonString(String opinion) {
        return opinion.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }

    private double[] parsePredictions(String jsonResponse) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonResponse);
        JsonNode predictionsNode = rootNode.path("prediction").get(0);

        double[] predictions = new double[predictionsNode.size()];
        for (int i = 0; i < predictionsNode.size(); i++) {
            predictions[i] = predictionsNode.get(i).asDouble();
        }
        return predictions;
    }

    private OpinionType determineOpinionType(double[] predictions) {
        int maxIndex = 0;
        for (int i = 1; i < predictions.length; i++) {
            if (predictions[i] > predictions[maxIndex]) {
                maxIndex = i;
            }
        }

        return switch (maxIndex) {
            case 0 -> OpinionType.NEUTRAL;
            case 1 -> OpinionType.NEGATIVE;
            case 2 -> OpinionType.POSITIVE;
            case 3 -> OpinionType.AMBIGUOUS;
            default -> throw new IllegalStateException("Unexpected value: " + maxIndex);
        };
    }
}
