package pl.pwr.zpi.cinemapro.domain.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pwr.zpi.cinemapro.domain.ticket.AlternativeTicketForm;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlternativeReservationForm {

    @NotNull
    private UUID showingId;

    @NotNull
    private Set<AlternativeTicketForm> reservations;
}
