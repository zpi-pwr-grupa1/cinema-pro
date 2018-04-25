package pl.pwr.zpi.cinemapro.domain.ticket.type;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@Data
public class TicketType {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private boolean visible;
}
