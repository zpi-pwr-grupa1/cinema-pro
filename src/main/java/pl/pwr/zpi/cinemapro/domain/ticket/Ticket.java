package pl.pwr.zpi.cinemapro.domain.ticket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.pwr.zpi.cinemapro.domain.reservation.Reservation;
import pl.pwr.zpi.cinemapro.domain.seat.Seat;
import pl.pwr.zpi.cinemapro.domain.ticket.type.TicketType;

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

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private TicketType ticketType;
    }
