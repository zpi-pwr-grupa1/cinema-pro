package pl.pwr.zpi.cinemapro.domain.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.zpi.cinemapro.domain.ticket.Ticket;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public List<Reservation> findAll(){
        return reservationRepository.findAll();
    }

    public Reservation findById(UUID id){
        return reservationRepository.findById(id);
    }

    public Set<Ticket> findTicketsById(UUID id) {
        Reservation reservation = findById(id);
        return reservation.getTickets();
    }

    public List<Reservation> findByShowingId(UUID id) {
        return reservationRepository.findByShowingId(id);
    }

    public List<Reservation> findyByClientId(UUID id) {
        return reservationRepository.findByClientId(id);
    }

    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

}
