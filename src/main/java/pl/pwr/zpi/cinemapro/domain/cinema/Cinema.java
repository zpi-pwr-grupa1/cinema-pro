package pl.pwr.zpi.cinemapro.domain.cinema;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table
@Data
public class Cinema {

    @Id
    @GeneratedValue
    private UUID id;

    //Adres rozbity:
    //nazwa ulicy(street)
    @Column(nullable = false)
    private String street;
    
    //numer ulicy(streetNumber)[String bo może być np. 27-29 albo 3a]
    @Column(nullable = false)
    private String streetNumber;
    
    //kod pocztowy(postCode)[String bo zapis np. 22-324]
    @Column(nullable = false)
    private String postCode;
    
    //miasto(city)
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

    //URL do obrazku głównego(nie cała galeria, w wypadku galerii trzeba by utworzyć nową encję Galeria(Kino, URL)
    @Column(nullable = true)
    private String imgUrl;
}