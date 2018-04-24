package pl.pwr.zpi.cinemapro.domain.ticket;

import lombok.Data;
import pl.pwr.zpi.cinemapro.domain.seat.Seat;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@Data
public class Ticket {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;
}
