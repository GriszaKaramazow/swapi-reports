package pl.softwareplant.swapireports.service;

import org.springframework.stereotype.Service;
import pl.softwareplant.swapireports.dto.QueryDTO;
import pl.softwareplant.swapireports.dto.RespondDTO;
import pl.softwareplant.swapireports.model.Character;
import pl.softwareplant.swapireports.model.Film;
import pl.softwareplant.swapireports.model.Planet;
import pl.softwareplant.swapireports.model.Report;
import pl.softwareplant.swapireports.repository.CharacterRepository;
import pl.softwareplant.swapireports.repository.FilmRepository;
import pl.softwareplant.swapireports.repository.PlanetRepository;
import pl.softwareplant.swapireports.repository.ReportRepository;
import pl.softwareplant.swapireports.request.SwapiRequester;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<RespondDTO> characters = swapiRequester.getCharacters(queryDTO.getQuery_criteria_character_phrase());
        Set<RespondDTO> planets = swapiRequester.getPlanets(queryDTO.getQuery_criteria_planet_name());
        Set<Film> films = new HashSet<>();
        for (RespondDTO character : characters) {
            for (RespondDTO planet : planets) {
                if (character.getFilmId().equals(planet.getFilmId())) {
                    films.add(generateFilm(character, planet, character.getFilmId()));
                }
            }
        }
        reportRepository.save(createReport(id, queryDTO, films));
    }

    public void deleteAll() {
        reportRepository.deleteAll();
    }

    public void deleteById(Long id) {
        reportRepository.deleteById(id);
    }

    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    public Report findById(Long reportId) {
        return reportRepository.findById(reportId)
                .orElse(new Report());
    }

    @Transactional
    private Film generateFilm(RespondDTO characterDTO, RespondDTO planetDTO, Long filmId) throws IOException, InterruptedException {
        Character character = getCharacterFromRespondDTO(characterDTO);
        characterRepository.save(character);
        Planet planet = getPlanetFromRespondDTO(planetDTO);
        planetRepository.save(planet);
        String filmTitle = swapiRequester.getTitleFromFilmId(filmId);
        return filmRepository.save(getFilmFromCharacterAndPlanet(filmId, filmTitle, character, planet));

    }

    private Planet getPlanetFromRespondDTO(RespondDTO respondDTO) {
        return new Planet(respondDTO.getId(), respondDTO.getName());
    }

    private Character getCharacterFromRespondDTO(RespondDTO respondDTO) {
        return new Character(respondDTO.getId(), respondDTO.getName());
    }

    private Film getFilmFromCharacterAndPlanet(Long filmId, String filmTitle, Character character, Planet planet) {
        return new Film(filmId, filmTitle, character, planet);
    }

    private Report createReport(Long reportId, QueryDTO queryDTO, Set<Film> films) {
        return new Report(reportId, queryDTO.getQuery_criteria_character_phrase(), queryDTO.getQuery_criteria_character_phrase(), films);
    }

}
