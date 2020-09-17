package pl.softwareplant.swapireports.mapper;

import pl.softwareplant.swapireports.dto.ReportDTO;
import pl.softwareplant.swapireports.dto.ResultDTO;
import pl.softwareplant.swapireports.model.Report;
import pl.softwareplant.swapireports.model.Result;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReportMapper {

    public List<ReportDTO> mapReportListToReportDTOList(List<Report> reports) {
        List<ReportDTO> reportDTOs = new ArrayList<>();
        for (Report report : reports) {
            reportDTOs.add(mapReportToReportDTO(report));
        }
        return reportDTOs;
    }

    public ReportDTO mapReportToReportDTO(Report report) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setId(report.getId());
        reportDTO.setQueryCriteriaCharacterPhrase(report.getQueryCriteriaCharacterPhrase());
        reportDTO.setQueryCriteriaPlanetName(report.getQueryCriteriaPlanetName());
        reportDTO.setResults(mapResultSetToResultDTOSet(report.getResults()));
        return reportDTO;
    }

    private Set<ResultDTO> mapResultSetToResultDTOSet(Set<Result> results) {
        Set<ResultDTO> resultDTOs = new HashSet<>();
        for (Result result : results) {
            resultDTOs.add(mapResultToResultDTO(result));
        }
        return resultDTOs;
    }

    private ResultDTO mapResultToResultDTO(Result result) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setFilmId(result.getFilm().getId());
        resultDTO.setFilmName(result.getFilm().getName());
        resultDTO.setCharacterId(result.getCharacter().getId());
        resultDTO.setCharacterName(result.getCharacter().getCharacterName());
        resultDTO.setPlanetId(result.getPlanet().getId());
        resultDTO.setPlanetName(result.getPlanet().getName());
        return resultDTO;
    }

}
