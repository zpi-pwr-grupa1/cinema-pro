package pl.pwr.zpi.cinemapro.domain.showing;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;


@AllArgsConstructor
@Data
public class ShowingForm {

    @NotNull
    private Date screening;
}
