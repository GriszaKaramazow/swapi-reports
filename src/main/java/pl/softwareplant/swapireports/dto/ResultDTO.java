package pl.softwareplant.swapireports.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResultDTO {

    @JsonProperty("film_id")
    private Long filmId;

    @JsonProperty("film_name")
    private String filmName;

    @JsonProperty("character_id")
    private Long characterId;

    @JsonProperty("character_name")
    private String characterName;

    @JsonProperty("planet_id")
    private Long planetId;

    @JsonProperty("planet_name")
    private String planetName;

}
