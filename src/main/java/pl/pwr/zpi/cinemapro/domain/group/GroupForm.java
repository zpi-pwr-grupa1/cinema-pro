package pl.pwr.zpi.cinemapro.domain.group;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

// NOTE about DTOs
// https://auth0.com/blog/automatically-mapping-dto-to-entity-on-spring-boot-apis/

@AllArgsConstructor
@Data
public class GroupForm {
    @NotNull
    @Length(min=1)
    private String label;
}
