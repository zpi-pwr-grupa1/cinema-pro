package pl.pwr.zpi.cinemapro.domain.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
    Ticket findById(UUID id);
}
