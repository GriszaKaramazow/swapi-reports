package pl.softwareplant.swapireports.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Report {

    @Id
    private Long id;

    private String queryCriteriaCharacterPhrase;

    private String queryCriteriaPlanetName;

    @ManyToMany
    private List<Film> resultFilms;

}
