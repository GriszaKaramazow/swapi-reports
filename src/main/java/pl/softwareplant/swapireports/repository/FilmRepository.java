package pl.softwareplant.swapireports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.softwareplant.swapireports.model.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

}
