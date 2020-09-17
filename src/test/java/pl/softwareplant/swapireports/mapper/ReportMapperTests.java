package pl.softwareplant.swapireports.mapper;

import org.junit.jupiter.api.Test;
import pl.softwareplant.swapireports.dto.ReportDTO;
import pl.softwareplant.swapireports.dto.ResultDTO;
import pl.softwareplant.swapireports.model.Character;
import pl.softwareplant.swapireports.model.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportMapperTests {

    private final ReportMapper reportMapper = new ReportMapper();
    @Test
    public void mapReportToReportDTOTest() {

        // given
        Character character = new Character();
        character.setId(26L);
        character.setCharacterName("Lobot");
        Film film = new Film();
        film.setId(2L);
        film.setName("The Empire Strikes Back");
        Planet planet = new Planet();
        planet.setId(6L);
        planet.setName("Bespin");
        Result result = new Result();
        result.setId(1L);
        result.setCharacter(character);
        result.setFilm(film);
        result.setPlanet(planet);
        Set<Result> results = new HashSet<>();
        results.add(result);
        Report testSubject = new Report();
        testSubject.setId(1L);
        testSubject.setQueryCriteriaCharacterPhrase("lobot");
        testSubject.setQueryCriteriaPlanetName("bespin");
        testSubject.setResults(results);


        // when
        ReportDTO mapperReportDTO = reportMapper.mapReportToReportDTO(testSubject);

        // then
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCharacterId(26L);
        resultDTO.setCharacterName("Lobot");
        resultDTO.setFilmId(2L);
        resultDTO.setFilmName("The Empire Strikes Back");
        resultDTO.setPlanetId(6L);
        resultDTO.setPlanetName("Bespin");
        Set<ResultDTO> resultDTOs = new HashSet<>();
        ReportDTO expectedReportDTO = new ReportDTO();
        expectedReportDTO.setId(1L);
        expectedReportDTO.setQueryCriteriaCharacterPhrase("lobot");
        expectedReportDTO.setQueryCriteriaPlanetName("bespin");
        expectedReportDTO.setResults(resultDTOs);

        assertEquals(mapperReportDTO.getId(), expectedReportDTO.getId());
        assertEquals(mapperReportDTO.getQueryCriteriaCharacterPhrase(), expectedReportDTO.getQueryCriteriaCharacterPhrase());
        assertEquals(mapperReportDTO.getQueryCriteriaPlanetName(), expectedReportDTO.getQueryCriteriaPlanetName());
        assertTrue(mapperReportDTO.getResults().containsAll(expectedReportDTO.getResults()));

    }
    
}
