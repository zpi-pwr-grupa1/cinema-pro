package pl.pwr.zpi.cinemapro.domain.movie;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table
@Data
public class Movie {

    @Id
    @GeneratedValue
    private UUID id;

    //tytuł
    @Column(unique = true, nullable = false)
    private String title;

    //dopuszczalna grupa wiekowa
    @Column(nullable = true)
    private String age;

    //miejsce powstania
    @Column(nullable = true)
    private String country;
    
    //czas trwania w minutach (wygodniej wpisać i przedstawić 90min, niż 01:30:00)
    @Column(nullable = true)
    private int runTime;

    //premiera polska
    @Column(nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date polishReleaseDate;
    
    //premiera światowa
    @Column(nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date worldReleaseDate;
    
    //krótki opis fabuły
    @Column(nullable = true)
    private String storyline;
    
    //url do zdjęcia
    @Column(nullable = true)
    private String imgURL;
    
    //reżyser
    @Column(nullable = true)
    private String director;
    
    //obsada
    @Column(nullable = true)
    private String movieCast;
    
    //widoczność
    @Column(nullable = false)
    @Value("true")
    private boolean visible;
}