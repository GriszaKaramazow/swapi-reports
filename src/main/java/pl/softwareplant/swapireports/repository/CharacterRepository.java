package pl.softwareplant.swapireports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.softwareplant.swapireports.model.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

}
