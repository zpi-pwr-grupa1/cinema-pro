package pl.pwr.zpi.cinemapro.domain.reservation;

import pl.pwr.zpi.cinemapro.domain.client.Client;
import pl.pwr.zpi.cinemapro.domain.showing.Showing;
import pl.pwr.zpi.cinemapro.domain.ticket.Ticket;

import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Reservation {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "showing_id")
    private Showing showing;

    @OneToMany
    private Set<Ticket> tickets;
}
