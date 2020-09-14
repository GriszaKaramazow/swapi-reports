package pl.softwareplant.swapireports.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FilmDTO {

    private Long filmId;

    private String filmName;

    private Long characterId;

    private String characterName;

    private Long planetId;

    private String planetName;

}
