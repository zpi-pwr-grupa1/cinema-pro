package pl.pwr.zpi.cinemapro.domain.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.Date;

// NOTE about DTOs
// https://auth0.com/blog/automatically-mapping-dto-to-entity-on-spring-boot-apis/

@AllArgsConstructor
@Data
public class MovieForm {

    @NotNull
    private String title;

    private String age;

    private String country;
    
    private int runTime;
    
    private Date polishReleaseDate;
    
    private Date worldReleaseDate;
    
    private String storyline;
    
    private String imgURL;
    
    private String director;
    
    private String movieCast;
    
    @NotNull
    private boolean visible;
}