package pl.softwareplant.swapireports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.softwareplant.swapireports.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

}
