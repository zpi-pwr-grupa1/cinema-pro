package pl.pwr.zpi.cinemapro.domain.ticket.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.zpi.cinemapro.domain.ticket.Ticket;

import java.util.List;
import java.util.UUID;

@Service
public class TicketTypeService {

    @Autowired
    TicketTypeRepository ticketTypeRepository;

    public List<TicketType> findAll() {
        return ticketTypeRepository.findAll();
    }

    public List<TicketType> findAllVisible() {
        return ticketTypeRepository.findByVisible(true);
    }

    public TicketType findById(UUID id) {
        return ticketTypeRepository.findById(id);
    }

    public TicketType save(TicketType ticket) {
        return ticketTypeRepository.save(ticket);
    }

    public void delete(TicketType ticket) {
        ticketTypeRepository.delete(ticket);
    }

    public void setNotVisible(TicketType ticketType) {
        ticketType.setVisible(false);
        ticketTypeRepository.save(ticketType);
    }
}
