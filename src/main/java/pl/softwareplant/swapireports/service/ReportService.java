package pl.softwareplant.swapireports.service;

import org.springframework.stereotype.Service;
import pl.softwareplant.swapireports.dto.QueryDTO;
import pl.softwareplant.swapireports.dto.RespondDTO;
import pl.softwareplant.swapireports.model.Character;
import pl.softwareplant.swapireports.model.*;
import pl.softwareplant.swapireports.repository.*;
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
    private final ResultRepository resultRepository;
    private final SwapiRequester swapiRequester;


    public ReportService(CharacterRepository characterRepository,
                         FilmRepository filmRepository,
                         PlanetRepository planetRepository,
                         ReportRepository reportRepository,
                         ResultRepository resultRepository,
                         SwapiRequester swapiRequester) {
        this.characterRepository = characterRepository;
        this.filmRepository = filmRepository;
        this.planetRepository = planetRepository;
        this.reportRepository = reportRepository;
        this.resultRepository = resultRepository;
        this.swapiRequester = swapiRequester;
    }

    public void saveOrUpdate(Long id, QueryDTO queryDTO) throws IOException, InterruptedException {
        Set<RespondDTO> characters = swapiRequester.getCharacters(queryDTO.getQuery_criteria_character_phrase());
        Set<RespondDTO> planets = swapiRequester.getPlanets(queryDTO.getQuery_criteria_planet_name());
        Set<Result> films = new HashSet<>();
        for (RespondDTO character : characters) {
            for (RespondDTO planet : planets) {
                if (character.getFilmId().equals(planet.getFilmId())) {
                    films.add(generateResult(character, planet, character.getFilmId()));
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
    private Result generateResult(RespondDTO characterDTO, RespondDTO planetDTO, Long filmId) throws IOException, InterruptedException {
        Character character = getCharacterFromRespondDTO(characterDTO);
        characterRepository.save(character);
        Planet planet = getPlanetFromRespondDTO(planetDTO);
        planetRepository.save(planet);
        Film film = getFilmFromFilmId(filmId);
        filmRepository.save(film);
        return resultRepository.save(getResult(film, character, planet));

    }

    private Film getFilmFromFilmId(Long filmId) throws IOException, InterruptedException {
        if (filmRepository.existsById(filmId)) {
            return filmRepository.getOne(filmId);
        }
        Film film = new Film();
        film.setId(filmId);
        film.setName(swapiRequester.getTitleFromFilmId(filmId));
        return film;
    }

    private Planet getPlanetFromRespondDTO(RespondDTO respondDTO) {
        if (planetRepository.existsById(respondDTO.getId())) {
            return planetRepository.getOne(respondDTO.getId());
        }
        Planet planet = new Planet();
        planet.setId(respondDTO.getId());
        planet.setName(respondDTO.getName());
        return planet;
    }

    private Character getCharacterFromRespondDTO(RespondDTO respondDTO) {
        if (characterRepository.existsById(respondDTO.getId())) {
            return characterRepository.getOne(respondDTO.getId());
        }
        Character character = new Character();
        character.setId(respondDTO.getId());
        character.setCharacterName(respondDTO.getName());
        return character;
    }

    private Result getResult(Film film, Character character, Planet planet) {
        Result result = new Result();
        result.setFilm(film);
        result.setCharacter(character);
        result.setPlanet(planet);
        return result;
    }

    private Report createReport(Long reportId, QueryDTO queryDTO, Set<Result> films) {
        return new Report(reportId, queryDTO.getQuery_criteria_character_phrase(), queryDTO.getQuery_criteria_character_phrase(), films);
    }

}
