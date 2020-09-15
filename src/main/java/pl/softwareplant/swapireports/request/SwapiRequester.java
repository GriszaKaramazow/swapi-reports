package pl.softwareplant.swapireports.request;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SwapiRequester {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();

    private final String CHARACTER_SEARCH_ENDPOINT = "/people/?search=";
    private final String PLANET_SEARCH_ENDPOINT = "/planets/?search=";

    public String getCharacter(String query) throws IOException, InterruptedException {
        return getFromSwapi(CHARACTER_SEARCH_ENDPOINT, query);
    }

    public String getPlanet(String query) throws IOException, InterruptedException {
        return getFromSwapi(PLANET_SEARCH_ENDPOINT, query);
    }

    public String getFromSwapi(String endpoint, String query) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api" + endpoint + query))
                .GET()
                .build();
        HttpResponse<String> httpResponse = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return httpResponse.body();

    }

}
