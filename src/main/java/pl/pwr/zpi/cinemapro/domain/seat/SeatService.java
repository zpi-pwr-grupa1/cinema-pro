package pl.pwr.zpi.cinemapro.domain.seat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    public List<Seat> findAllVisible() {
        return seatRepository.findByVisible(true);
    }

    public Seat findById(UUID id){
        return seatRepository.findById(id);
    }


    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    public void setNotVisible(Seat seat) {
        seat.setVisible(false);
        seatRepository.save(seat);
    }

}
