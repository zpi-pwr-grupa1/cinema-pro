package pl.pwr.zpi.cinemapro.domain.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CinemaService {

    @Autowired
    CinemaRepository cinemaRepository;

    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    public List<Cinema> findAllVisible() {
        return cinemaRepository.findByVisible(true);
    }
    public Cinema findByID(UUID id){
        return cinemaRepository.findById(id);
    }

    public Cinema save(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    public void setNotVisible(Cinema cinema) {
        cinema.setVisible(false);
        cinemaRepository.save(cinema);
    }

}
