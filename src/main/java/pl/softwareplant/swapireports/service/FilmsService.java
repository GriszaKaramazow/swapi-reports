package pl.softwareplant.swapireports.service;

import pl.softwareplant.swapireports.repository.FilmRepository;

public class FilmsService {

    private final FilmRepository filmRepository;

    public FilmsService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

}
