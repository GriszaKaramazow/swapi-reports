package pl.softwareplant.swapireports.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.softwareplant.swapireports.model.Report;
import pl.softwareplant.swapireports.service.ReportService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;;
    }

    @PutMapping("/{report_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrCreateReport(@PathVariable Long reportId) {
        System.out.println("PUT at /reports");
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        System.out.println("DELETE at /reports");
        reportService.deleteAll();
    }

    @DeleteMapping("/{report_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long reportId) throws IOException, InterruptedException {
        System.out.println("DELETE at /reports/{report_id}");
        HttpClient httpClient = HttpClient.newBuilder()
                .build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/people/" + reportId))
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());
        reportService.deleteById(reportId);
    }

    @GetMapping
    public List<Report> findAll() {
        System.out.println("GET at /reports");
        return reportService.findAll();
    }

    @GetMapping("/{report_id}")
    public Report findById(@PathVariable Long reportId) {
        System.out.println("GET at /reports/{report_id}");
        return reportService.findById(reportId);
    }

}
