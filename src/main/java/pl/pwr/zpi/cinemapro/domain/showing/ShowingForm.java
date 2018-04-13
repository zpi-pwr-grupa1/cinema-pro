package pl.pwr.zpi.cinemapro.domain.showing;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pwr.zpi.cinemapro.domain.hall.Hall;
import pl.pwr.zpi.cinemapro.domain.movie.Movie;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;


@AllArgsConstructor
@Data
public class ShowingForm {

    @NotNull
    private Date screeningStart;

    @NotNull
    private Movie movie;

    @NotNull
    private Hall hall;
}
