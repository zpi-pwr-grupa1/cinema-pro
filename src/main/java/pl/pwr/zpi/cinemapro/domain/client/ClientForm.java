package pl.pwr.zpi.cinemapro.domain.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

// NOTE about DTOs
// https://auth0.com/blog/automatically-mapping-dto-to-entity-on-spring-boot-apis/

@AllArgsConstructor
@Data
public class ClientForm {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Date birthDate;
}
