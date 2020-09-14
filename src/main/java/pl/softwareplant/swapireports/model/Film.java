package pl.softwareplant.swapireports.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Film {

    @Id
    private Long id;

    private String filmTitle;

    @ManyToOne
    private Character character;

    @ManyToOne
    private Planet planet;

}
