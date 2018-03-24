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

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = true)
    private String age;

    @Column(nullable = true)
    private String country;
    
    @Column(nullable = true)
    private int runTime;

    @Column(nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date polishReleaseDate;
    
    @Column(nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date worldReleaseDate;
    
    @Column(nullable = true)
    private String storyline;
    
    @Column(nullable = true)
    private String imgURL;
    
    @Column(nullable = true)
    private String director;
    
    @Column(nullable = true)
    private String movieCast;
    
    @Column(nullable = false)
    @Value("true")
    private boolean visible;
}