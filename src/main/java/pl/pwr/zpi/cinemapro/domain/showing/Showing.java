package pl.pwr.zpi.cinemapro.domain.showing;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.pwr.zpi.cinemapro.domain.hall.Hall;
import pl.pwr.zpi.cinemapro.domain.movie.Movie;
import pl.pwr.zpi.cinemapro.domain.reservation.Reservation;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
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

    @OneToMany(mappedBy = "showing")
    @JsonIgnore
    private Set<Reservation> reservations;
}
