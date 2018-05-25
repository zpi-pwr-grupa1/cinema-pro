package pl.pwr.zpi.cinemapro.domain.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.pwr.zpi.cinemapro.common.security.Roles;
import pl.pwr.zpi.cinemapro.domain.moviegroup.MovieGroup;
import pl.pwr.zpi.cinemapro.domain.reservation.Reservation;
import pl.pwr.zpi.cinemapro.domain.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Data
@DiscriminatorValue(Roles.CLIENT)
public class Client extends User {

    @Id
    @GeneratedValue
    private UUID id;

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