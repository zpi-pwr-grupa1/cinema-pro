package pl.pwr.zpi.cinemapro.domain.employee;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import pl.pwr.zpi.cinemapro.domain.cinema.Cinema;

// NOTE about DTOs
// https://auth0.com/blog/automatically-mapping-dto-to-entity-on-spring-boot-apis/

@AllArgsConstructor
@Data
public class EmployeeForm {
    

    @NotNull
    private Cinema cinema;
    
    @NotNull
    @Length(min=1)
    private String name;
    
    @NotNull
    @Length(min=1)
    private String surname;
    
    @NotNull
    @Length(min=1)
    private String email;
    
    @NotNull
    private Date startingDateOfEmployment;
    
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
}
