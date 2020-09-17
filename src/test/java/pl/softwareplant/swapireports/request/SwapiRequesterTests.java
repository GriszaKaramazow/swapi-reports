package pl.softwareplant.swapireports.request;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.softwareplant.swapireports.dto.RespondDTO;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class SwapiRequesterTests {


    private final WireMockServer mockedServer = new WireMockServer();

    @BeforeAll
    public void startServer() {
        mockedServer.start();
    }

    @AfterAll
    public void stopServer() {
        mockedServer.stop();
    }

    @Test
    public void getCharactersTest() throws IOException, InterruptedException {
        // given
        stubFor(get(urlEqualTo("/api/people/?search=r5"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("getCharactersTest.json")));

        SwapiRequester swapiRequester = new SwapiRequester();
        // when
        Set<RespondDTO> resultRespondDTOs = swapiRequester.getCharacters("r5");
        // then
        Set<RespondDTO> expectedRespondDTOs = new HashSet<>();
        RespondDTO respondDTO = new RespondDTO();
        respondDTO.setId(8L);
        respondDTO.setName("R5-D4");
        respondDTO.setFilmId(1L);
        expectedRespondDTOs.add(respondDTO);
        assertEquals(resultRespondDTOs, expectedRespondDTOs);
    }

    @Test
    public void getPlanetTest() throws IOException, InterruptedException {
        // given
        stubFor(get(urlEqualTo("api/planets/?search=endor"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("getPlanetTest.json")));

        SwapiRequester swapiRequester = new SwapiRequester();
        // when
        Set<RespondDTO> resultRespondDTOs = swapiRequester.getCharacters("endor");
        // then
        Set<RespondDTO> expectedRespondDTOs = new HashSet<>();
        RespondDTO respondDTO = new RespondDTO();
        respondDTO.setId(7L);
        respondDTO.setName("Endor");
        respondDTO.setFilmId(3L);
        expectedRespondDTOs.add(respondDTO);
        assertEquals(resultRespondDTOs, expectedRespondDTOs);
    }

    @Test
    public void getTitleFromFilmIdTest() throws IOException, InterruptedException {
        // given
        stubFor(get(urlEqualTo("api/planets/?search=endor"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("getTitleFromFilmIdTest.json")));

        SwapiRequester swapiRequester = new SwapiRequester();
        // when
        String resultFilmName = swapiRequester.getTitleFromFilmId(3L);
        // then
        assertEquals(resultFilmName, "Return of the Jedi ");
    }

}
