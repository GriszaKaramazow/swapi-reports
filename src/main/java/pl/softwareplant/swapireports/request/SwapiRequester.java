package pl.softwareplant.swapireports.request;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.softwareplant.swapireports.dto.RespondDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;

public class SwapiRequester {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();

    private final String CHARACTER_SEARCH_ENDPOINT = "/people/?search=";
    private final String PLANET_SEARCH_ENDPOINT = "/planets/?search=";
    
    private Set<RespondDTO> getCharacterOrPlanet(String endpoint, String query) throws IOException, InterruptedException {
        Set<RespondDTO> respondDTOs = new HashSet<>();
        String bodyString = getFromSwapi(endpoint, query);
        JSONObject bodyJson = new JSONObject(bodyString);
        JSONArray resultArray = bodyJson.getJSONArray("results");
        int resultCount = bodyJson.getInt("count");
        for (int i = 0; i < resultCount; i++) {
            RespondDTO respondDTO = new RespondDTO();
            respondDTO.setId(Long.parseLong(resultArray.getJSONObject(i)
                    .getString("url")
                    .substring("http://localhost:8080/api".length())
                    .replaceAll("\\D+","")));
            respondDTO.setName(resultArray.getJSONObject(i)
                    .getString("name"));
            Set<Long> films = new HashSet<>();
            JSONArray filmsArray = resultArray.getJSONObject(i).getJSONArray("films");
            for (int j = 0; j < filmsArray.length(); j++) {
                films.add(Long.parseLong(filmsArray.getString(j)
                        .substring("http://localhost:8080/api".length())
                        .replaceAll("\\D+","")));
            }
            respondDTOs.add(respondDTO);
        }
        return respondDTOs;
    }


    public Set<RespondDTO> getCharacter(String query) throws IOException, InterruptedException {
        return getCharacterOrPlanet(CHARACTER_SEARCH_ENDPOINT, query);
    }

    public Set<RespondDTO> getPlanet(String query) throws IOException, InterruptedException {
        return getCharacterOrPlanet(PLANET_SEARCH_ENDPOINT, query);
    }

    public String getTitleFromFilmId(Long filmId) throws IOException, InterruptedException {
        String bodyString = getFromSwapi("/films/", filmId.toString());
        JSONObject bodyJson = new JSONObject(bodyString);
        return bodyJson.getString("title");
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
