package pl.pwr.zpi.cinemapro.domain.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import pl.pwr.zpi.cinemapro.domain.moviegroup.MovieGroup;
import pl.pwr.zpi.cinemapro.domain.reservation.Reservation;

@Entity
@Table
@Data
public class Client {

    @Id
    @GeneratedValue
    private UUID id;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date birthDate;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_group_id")
    private Set<MovieGroup> groups;

    @OneToMany
    @JsonIgnore
    @JoinColumn(name = "client_id")
    private Set<Reservation> reservations;
}