package pl.softwareplant.swapireports.request;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class CharacterRequester {

    private final HttpClient httpClient;

    public CharacterRequester(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpRequest buildHttpRequest(int id) {
        return HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/people/" + id))
                .build();
    }

    public void printBody(int id) {
        HttpRequest httpRequest = buildHttpRequest(id);


    }

}
