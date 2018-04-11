package pl.pwr.zpi.cinemapro.domain.hall;

import java.util.Set;
import pl.pwr.zpi.cinemapro.domain.seat.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.util.UUID;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import pl.pwr.zpi.cinemapro.domain.cinema.Cinema;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hall {

    @Id
    @GeneratedValue
    private UUID id;

    /*
    @OneToMany(mappedBy = "hall")
    private Set<Seat> seats;
    */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CINEMA_ID")
    private Cinema cinema;

    @Column(nullable = false)
    private int hallNumber;

    @Column(nullable = false)
    @Value("true")
    private boolean visible;
}