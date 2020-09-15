package pl.softwareplant.swapireports.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.softwareplant.swapireports.dto.QueryDTO;
import pl.softwareplant.swapireports.model.Report;
import pl.softwareplant.swapireports.service.ReportService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;;
    }

    @PutMapping("/{reportId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrCreateReport(@PathVariable Long reportId, @RequestBody QueryDTO queryDTO) throws IOException, InterruptedException {
        System.out.println("PUT at /reports/" + reportId);
        reportService.saveOrUpdate(reportId, queryDTO);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        System.out.println("DELETE at /reports");
        reportService.deleteAll();
    }

    @DeleteMapping("/{reportId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long reportId) {
        System.out.println("DELETE at /reports/" + reportId);
        reportService.deleteById(reportId);
    }

    @GetMapping
    public List<Report> findAll() {
        System.out.println("GET at /reports");
        return reportService.findAll();
    }

    @GetMapping("/{reportId}")
    public Report findById(@PathVariable Long reportId) {
        System.out.println("GET at /reports/" + reportId);
        return reportService.findById(reportId);
    }

}
