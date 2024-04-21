package pl.jorgX;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ModelAPIConnector {
    private final HttpClient client;
    private final String apiUrl;

    public ModelAPIConnector(String apiUrl) {
        this.client = HttpClient.newHttpClient();
        this.apiUrl = apiUrl;
    }

    public String makeRequest(String text) {
        String json = "{\"text\":\"" + text + "\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status Code: " + response.statusCode());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error in API communication: " + e.getMessage();
        }
    }
}