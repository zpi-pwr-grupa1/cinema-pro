package pl.pwr.zpi.cinemapro.domain.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlternativeTicketForm {

    @NotNull
    private UUID seatId;

    @NotNull
    private UUID ticketTypeId;
}
