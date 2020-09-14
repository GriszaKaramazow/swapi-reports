package pl.softwareplant.swapireports.service;

import pl.softwareplant.swapireports.repository.ReportRepository;

public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

}
