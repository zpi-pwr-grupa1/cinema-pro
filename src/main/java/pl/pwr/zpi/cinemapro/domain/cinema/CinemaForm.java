package pl.pwr.zpi.cinemapro.domain.cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

// NOTE about DTOs
// https://auth0.com/blog/automatically-mapping-dto-to-entity-on-spring-boot-apis/

@AllArgsConstructor
@Data
public class CinemaForm {

    @NotNull
    @Length(min=1)
    private String name;

    @NotNull
    @Length(min=1)
    private String street;

    @NotNull
    @Length(min=1)
    private String streetNumber;

    @NotNull
    @Length(min=1)
    private String postCode;

    @NotNull
    @Length(min=1)
    private String city;

    @NotNull
    @Length(min=1)
    private String telephone;

    @NotNull
    @Length(min=1)
    private String description;

    @Email
    @NotNull
    @Length(min=1)
    private String email;

    @NotNull
    private boolean visible;

    @NotNull
    @Length(min=1)
    private String imgUrl;

}
