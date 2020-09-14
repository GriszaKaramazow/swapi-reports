package pl.softwareplant.swapireports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.softwareplant.swapireports.model.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {

}
