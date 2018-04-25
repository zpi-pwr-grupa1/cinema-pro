package pl.pwr.zpi.cinemapro.domain.ticket.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType, Long>{
    TicketType findById(UUID id);

    List<TicketType> findByVisible(boolean b);
}
