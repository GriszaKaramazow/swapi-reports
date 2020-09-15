package pl.softwareplant.swapireports.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CharacterDTO {

    private String id;

    private String name;

    private Set<String> films;

}
