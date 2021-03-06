package pl.pwr.zpi.cinemapro.domain.employee;

import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.util.UUID;
import javax.validation.constraints.Email;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import pl.pwr.zpi.cinemapro.domain.cinema.Cinema;
import pl.pwr.zpi.cinemapro.domain.user.User;

@Entity
@Table
@Data
@DiscriminatorValue("EMPLOYEE")
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String surname;
    
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startingDateOfEmployment;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @Column(nullable = false)
    @Value("true")
    private boolean visible;
    
    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String streetNumber;

    @Column(nullable = false)
    private String postCode;
	
    @Column(nullable = false)
    private String city;
    
    @Column(nullable = false)
    private String telephone;
}