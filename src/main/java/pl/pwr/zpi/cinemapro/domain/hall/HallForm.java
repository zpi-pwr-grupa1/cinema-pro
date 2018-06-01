package pl.pwr.zpi.cinemapro.domain.hall;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import pl.pwr.zpi.cinemapro.domain.cinema.Cinema;

@AllArgsConstructor
@Data
public class HallForm {
    @NotNull
    @Min(0)
    private int hallNumber;

    @NotNull
    private Cinema cinema;
}
