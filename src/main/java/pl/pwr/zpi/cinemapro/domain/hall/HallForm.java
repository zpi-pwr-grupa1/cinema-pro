package pl.pwr.zpi.cinemapro.domain.hall;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import pl.pwr.zpi.cinemapro.domain.cinema.Cinema;

// NOTE about DTOs
// https://auth0.com/blog/automatically-mapping-dto-to-entity-on-spring-boot-apis/

@AllArgsConstructor
@Data
public class HallForm {
    @NotNull
    private int hallNumber;

    @NotNull
    private Cinema cinema;
}
