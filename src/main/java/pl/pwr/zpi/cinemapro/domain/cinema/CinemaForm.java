package pl.pwr.zpi.cinemapro.domain.cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

// NOTE about DTOs
// https://auth0.com/blog/automatically-mapping-dto-to-entity-on-spring-boot-apis/

@AllArgsConstructor
@Data
public class CinemaForm {
    @NotNull
    private String name;

    @NotNull
    private String street;

    @NotNull
    private String streetNumber;

    @NotNull
    private String postCode;

    @NotNull
    private String city;

    @NotNull
    private String telephone;

    @NotNull
    private String description;

    @Email
    @NotNull
    private String email;

    @NotNull
    private boolean visible;

    @NotNull
    private String imgUrl;

}
