package pl.pwr.zpi.cinemapro.domain.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pwr.zpi.cinemapro.domain.seat.Seat;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class TicketForm {
    @NotNull
    private Seat seat;

}
