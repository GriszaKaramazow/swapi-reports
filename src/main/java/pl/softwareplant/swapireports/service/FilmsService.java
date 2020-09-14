package pl.softwareplant.swapireports.service;

import org.springframework.stereotype.Service;
import pl.softwareplant.swapireports.repository.FilmRepository;

@Service
public class FilmsService {

    private final FilmRepository filmRepository;

    public FilmsService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

}
