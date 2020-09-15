package pl.softwareplant.swapireports.service;

import org.springframework.stereotype.Service;
import pl.softwareplant.swapireports.dto.QueryDTO;
import pl.softwareplant.swapireports.model.Report;
import pl.softwareplant.swapireports.repository.CharacterRepository;
import pl.softwareplant.swapireports.repository.FilmRepository;
import pl.softwareplant.swapireports.repository.PlanetRepository;
import pl.softwareplant.swapireports.repository.ReportRepository;
import pl.softwareplant.swapireports.request.SwapiRequester;

import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    private final CharacterRepository characterRepository;
    private final FilmRepository filmRepository;
    private final PlanetRepository planetRepository;
    private final ReportRepository reportRepository;
    private final SwapiRequester swapiRequester;


    public ReportService(CharacterRepository characterRepository,
                         FilmRepository filmRepository,
                         PlanetRepository planetRepository,
                         ReportRepository reportRepository,
                         SwapiRequester swapiRequester) {
        this.characterRepository = characterRepository;
        this.filmRepository = filmRepository;
        this.planetRepository = planetRepository;
        this.reportRepository = reportRepository;
        this.swapiRequester = swapiRequester;
    }

    public void saveOrUpdate(Long id, QueryDTO queryDTO) throws IOException, InterruptedException {

        System.out.println(queryDTO.getQuery_criteria_character_phrase());
        System.out.println(swapiRequester.getCharacter(queryDTO.getQuery_criteria_character_phrase()));

        System.out.println(queryDTO.getQuery_criteria_planet_name());
        System.out.println(swapiRequester.getPlanet(queryDTO.getQuery_criteria_planet_name()));
    }

    public void deleteAll() {
        reportRepository.deleteAll();
    }

    public void deleteById(Long id) throws IOException {
        reportRepository.deleteById(id);
    }

    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    public Report findById(Long reportId) {
        return reportRepository.findById(reportId)
                .orElse(new Report());
    }

}
