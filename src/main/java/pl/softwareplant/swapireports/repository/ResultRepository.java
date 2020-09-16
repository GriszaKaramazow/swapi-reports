package pl.softwareplant.swapireports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.softwareplant.swapireports.model.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

}
