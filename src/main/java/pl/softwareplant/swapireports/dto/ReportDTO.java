package pl.softwareplant.swapireports.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReportDTO {

    private Long id;

    private String queryCriteriaCharacterPhrase;

    private String queryCriteriaPlanetName;

    private List<FilmDTO> resultFilms;

}
