package pl.pwr.zpi.cinemapro.domain.cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;
import java.util.UUID;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import pl.pwr.zpi.cinemapro.domain.hall.Hall;

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

    @OneToMany(mappedBy = "cinema", cascade=CascadeType.ALL)
    @JsonIgnore
    Set<Hall> halls;

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
    @Value("${true}")
    private boolean visible;

    @Column(nullable = true)
    private String imgUrl;
}