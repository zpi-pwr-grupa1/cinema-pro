package pl.pwr.zpi.cinemapro.domain.ticket.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pwr.zpi.cinemapro.domain.seat.Seat;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class TicketTypeForm {
    @NotNull
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private boolean visible;
}
