package pl.pwr.zpi.cinemapro.domain.cinema;

import pl.pwr.zpi.cinemapro.domain.cinema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {

    @Autowired
    CinemaRepository cinemaRepository;

    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    public Cinema save(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

}
