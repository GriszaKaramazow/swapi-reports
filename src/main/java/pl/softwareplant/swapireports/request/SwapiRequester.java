package pl.softwareplant.swapireports.request;

import org.json.JSONArray;
import org.json.JSONObject;

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
    
    public String getCharacterOrPlanet(String endpoint, String query) throws IOException, InterruptedException {
        String bodyString = getFromSwapi(endpoint, query);
        JSONObject bodyJson = new JSONObject(bodyString);
        JSONArray resultArray = bodyJson.getJSONArray("results");
        int resultCount = bodyJson.getInt("count");
        for (int i = 0; i < resultCount; i++) {
            System.out.println(resultArray.getJSONObject(i).getString("name"));
            System.out.println(resultArray.getJSONObject(i).getString("url"));
            JSONArray filmsArray = resultArray.getJSONObject(i).getJSONArray("films");
            for (int j = 0; j < filmsArray.length(); j++) {
                System.out.println(filmsArray.getString(j));
            }
        }
        return query;
    }

    public String getCharacter(String query) throws IOException, InterruptedException {
        return getCharacterOrPlanet(CHARACTER_SEARCH_ENDPOINT, query);
    }

    public String getPlanet(String query) throws IOException, InterruptedException {
        return getCharacterOrPlanet(PLANET_SEARCH_ENDPOINT, query);
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
