package pl.softwareplant.swapireports.request;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import pl.softwareplant.swapireports.dto.RespondDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SwapiRequester {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();

    @Value("${swapi.character.request}")
    private String CHARACTER_REQUEST_ENDPOINT;

    @Value("${swapi.planet.request}")
    private String PLANET_REQUEST_ENDPOINT;

    @Value("${swapi.url.address}")
    private String SWAPI_URL_ADDRESS;
    
    private Set<RespondDTO> getCharactersOrPlanets(String endpoint, String query) throws IOException, InterruptedException {

        Set<RespondDTO> respondDTOs = new HashSet<>();
        String bodyString = getFromSwapi(endpoint, query);
        JSONObject bodyJson = new JSONObject(bodyString);
        JSONArray resultArray = bodyJson.getJSONArray("results");
        int resultCount = bodyJson.getInt("count");
        for (int i = 0; i < resultCount; i++) {
            Long resourceId = getIdFromUrl(resultArray.getJSONObject(i)
                    .getString("url"));
            String resourceName = resultArray.getJSONObject(i)
                    .getString("name");
            JSONArray filmsArray = resultArray.getJSONObject(i).getJSONArray("films");
            for (int j = 0; j < filmsArray.length(); j++) {
                respondDTOs.add(new RespondDTO(resourceId, resourceName, getIdFromUrl(filmsArray.getString(j))));
            }
        }
        return respondDTOs;
    }

    private Long getIdFromUrl(String url) {
        return Long.parseLong(url.substring(SWAPI_URL_ADDRESS.length())
                .replaceAll("\\D+",""));
    }

    public Set<RespondDTO> getCharacters(String query) throws IOException, InterruptedException {
        return getCharactersOrPlanets(CHARACTER_REQUEST_ENDPOINT, query);
    }

    public Set<RespondDTO> getPlanets(String query) throws IOException, InterruptedException {
        return getCharactersOrPlanets(PLANET_REQUEST_ENDPOINT, query);
    }

    public Map<Long, String> getTitlesFromIds(Set<Long> filmIds) throws IOException, InterruptedException {
        Map<Long, String> filmTitles = new HashMap<>();
        for (Long filmId : filmIds) {
            filmTitles.put(filmId, getTitleFromFilmId(filmId));
        }
        return filmTitles;
    }

    public String getTitleFromFilmId(Long filmId) throws IOException, InterruptedException {
        String bodyString = getFromSwapi("/films/", filmId.toString());
        JSONObject bodyJson = new JSONObject(bodyString);
        return bodyJson.getString("title");
    }

    public String getFromSwapi(String endpoint, String query) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(SWAPI_URL_ADDRESS + endpoint + query))
                .GET()
                .build();
        HttpResponse<String> httpResponse = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return httpResponse.body();

    }

}
