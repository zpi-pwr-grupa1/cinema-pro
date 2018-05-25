package pl.pwr.zpi.cinemapro.domain.seat;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class ShowingSeatForm {

    private UUID id;

    private int seatRow;

    private int seatColumn;

    private boolean taken;
}
