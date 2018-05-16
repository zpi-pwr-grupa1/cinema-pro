package pl.pwr.zpi.cinemapro.domain.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.zpi.cinemapro.domain.reservation.Reservation;
import pl.pwr.zpi.cinemapro.domain.reservation.ReservationRepository;
import pl.pwr.zpi.cinemapro.domain.reservation.ReservationService;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ReservationService reservationService;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }
    
    public Client findById(UUID id){
        return clientRepository.findById(id);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Reservation> getReservationsByClientId(UUID id) {
        return reservationService.findyByClientId(id);
    }
}
