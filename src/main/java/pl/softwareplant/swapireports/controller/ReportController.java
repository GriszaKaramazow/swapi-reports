package pl.softwareplant.swapireports.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.softwareplant.swapireports.model.Report;
import pl.softwareplant.swapireports.service.ReportService;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PutMapping("/{report_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrCreateReport(@PathVariable Long reportId) {

    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        reportService.deleteAll();
    }

    @DeleteMapping("/{report_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long reportId) {
        reportService.deleteById(reportId);
    }

    @GetMapping
    public List<Report> findAll() {
        return reportService.findAll();
    }

    @GetMapping("/{report_id}")
    public Report findById(@PathVariable Long reportId) {
        return reportService.findById(reportId);
    }

}
