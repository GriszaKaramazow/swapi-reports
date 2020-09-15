package pl.softwareplant.swapireports.service;

import org.springframework.stereotype.Service;
import pl.softwareplant.swapireports.model.Report;
import pl.softwareplant.swapireports.repository.ReportRepository;

import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
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

}
