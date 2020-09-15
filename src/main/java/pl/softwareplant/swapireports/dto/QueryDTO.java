package pl.softwareplant.swapireports.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class QueryDTO {

    private String query_criteria_character_phrase;

    private String query_criteria_planet_name;

}
