package pl.pwr.zpi.cinemapro.domain.seat;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

// NOTE about DTOs
// https://auth0.com/blog/automatically-mapping-dto-to-entity-on-spring-boot-apis/

@AllArgsConstructor
@Data
public class SeatForm {
    @NotNull
    private int seatRow;

    @NotNull
    private int seatColumn;
}
