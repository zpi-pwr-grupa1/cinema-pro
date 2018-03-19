package pl.pwr.zpi.cinemapro.domain.cinema;

import pl.pwr.zpi.cinemapro.domain.cinema.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

// NOTE about DTOs
// https://auth0.com/blog/automatically-mapping-dto-to-entity-on-spring-boot-apis/

@AllArgsConstructor
@Data
public class CinemaForm {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String telephone;

    @NotNull
    private String address;
    
    private String description;
}
