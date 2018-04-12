package pl.pwr.zpi.cinemapro.domain.showing;

import lombok.Data;
import pl.pwr.zpi.cinemapro.domain.hall.Hall;
import pl.pwr.zpi.cinemapro.domain.movie.Movie;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
public class Showing {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private Date screeningStart;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;



}
