package pl.pwr.zpi.cinemapro.domain.seat;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.util.UUID;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import pl.pwr.zpi.cinemapro.domain.hall.Hall;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HALL_ID")
    private Hall hall;

    @Column(nullable = false)
    private int seatRow;

    @Column(nullable = false)
    private int seatColumn;

    @Column(nullable = false)
    @Value("true")
    private boolean visible;
}