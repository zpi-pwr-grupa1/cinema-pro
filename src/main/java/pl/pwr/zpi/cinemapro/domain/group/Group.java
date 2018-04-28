package pl.pwr.zpi.cinemapro.domain.group;

import pl.pwr.zpi.cinemapro.domain.hall.*;
import java.util.Set;
import com.fasterxml.jackson.annotation.*;
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
public class Group {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany
    private Set<Seat> seats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    @JsonIgnore
    private Cinema cinema;

    @Column(nullable = false)
    private int hallNumber;

    @Column(nullable = false)
    @Value("true")
    private boolean visible;
}