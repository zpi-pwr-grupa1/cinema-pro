package pl.pwr.zpi.cinemapro.domain.seat;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class SeatForm {
    @NotNull
    @Min(0)
    private int seatRow;

    @NotNull
    @Min(0)
    private int seatColumn;
}
