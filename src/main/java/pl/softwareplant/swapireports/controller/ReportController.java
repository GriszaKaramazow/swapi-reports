package pl.softwareplant.swapireports.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.softwareplant.swapireports.dto.QueryDTO;
import pl.softwareplant.swapireports.dto.ReportDTO;
import pl.softwareplant.swapireports.service.ReportService;

import java.io.IOException;
import java.util.List;

@Slf4j
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
        log.info("PUT request have been received at /reports/" + reportId);
        reportService.saveOrUpdate(reportId, queryDTO);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        log.info("DELETE request have been received at /reports/");
        reportService.deleteAll();
    }

    @DeleteMapping("/{reportId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long reportId) {
        log.info("DELETE request have been received at /reports/" + reportId);
        reportService.deleteById(reportId);
    }

    @GetMapping
    public List<ReportDTO> findAll() {
        log.info("GET request have been received at /reports/");
        return reportService.findAll();
    }

    @GetMapping("/{reportId}")
    public ReportDTO findById(@PathVariable Long reportId) {
        log.info("GET request have been received at /reports/" + reportId);
        return reportService.findById(reportId);
    }

}
