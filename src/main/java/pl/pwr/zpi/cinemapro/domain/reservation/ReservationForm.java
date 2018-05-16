package pl.pwr.zpi.cinemapro.domain.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pwr.zpi.cinemapro.domain.client.Client;
import pl.pwr.zpi.cinemapro.domain.showing.Showing;
import pl.pwr.zpi.cinemapro.domain.ticket.Ticket;

import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@Data
public class ReservationForm {

    @NotNull
    private Client client;

    @NotNull
    private Showing showing;

    @NotNull
    private Set<Ticket> tickets;
}
