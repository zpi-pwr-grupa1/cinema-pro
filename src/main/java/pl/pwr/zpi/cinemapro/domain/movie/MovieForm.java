package pl.pwr.zpi.cinemapro.domain.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

// NOTE about DTOs
// https://auth0.com/blog/automatically-mapping-dto-to-entity-on-spring-boot-apis/

@AllArgsConstructor
@Data
public class MovieForm {

    @NotNull
    @Length(min=1, max=255)
    private String title;

    private String age;

    private String country;
    
    private int runTime;
    
    private Date polishReleaseDate;
    
    private Date worldReleaseDate;

    @Length(max=255)
    private String storyline;
    
    private String imgURL;

    @Length(max=255)
    private String director;

    @Length(max=255)
    private String movieCast;
    
    @NotNull
    private boolean visible;
}