package pl.pwr.zpi.cinemapro.domain.seat;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.util.UUID;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID hall;

    @Column(nullable = false)
    private int seatRow;

    @Column(nullable = false)
    private int seatColumn;

    @Column(nullable = false)
    @Value("true")
    private boolean visible;
}