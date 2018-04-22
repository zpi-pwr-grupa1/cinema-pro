package pl.pwr.zpi.cinemapro.domain.seat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Seat> createSeats(int n, int k) {
        List<Seat> seats = new ArrayList<>();
        for(int i=0; i<n; i++) {
            for (int j = 0; j < k; j++) {
                Seat s = new Seat();
                s.setSeatColumn(i);
                s.setSeatRow(j);
                s.setVisible(true);
                seats.add(s);
            }
        }

        return seatRepository.saveAll(seats);
    }

}
