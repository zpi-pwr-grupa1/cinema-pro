package pl.pwr.zpi.cinemapro.domain.cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.UUID;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

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

    @Column(nullable = true)
    private String description;
    
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    @Value("true")
    private boolean visible;

    @Column(nullable = true)
    private String imgUrl;
}