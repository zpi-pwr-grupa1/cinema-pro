package pl.pwr.zpi.cinemapro.domain.ticket;

import lombok.Data;
import pl.pwr.zpi.cinemapro.domain.seat.Seat;
import pl.pwr.zpi.cinemapro.domain.ticket.type.TicketType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;

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

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private TicketType ticketType;
    
    @Column (nullable = false)
    @Value("true")
    private boolean paid;
    
    @Column (nullable = true)
    @Min(0)
    private double price;
    }
