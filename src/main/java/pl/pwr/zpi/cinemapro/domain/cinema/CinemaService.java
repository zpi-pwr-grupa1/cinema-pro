package pl.pwr.zpi.cinemapro.domain.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.zpi.cinemapro.domain.hall.Hall;
import pl.pwr.zpi.cinemapro.domain.hall.HallRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CinemaService {

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    HallRepository hallRepository;

    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    public List<Cinema> findAllVisible() {
        return cinemaRepository.findByVisible(true);
    }

    public Cinema findById(UUID id){
        return cinemaRepository.findById(id);
    }

    public Cinema findByName(String name) {
        return cinemaRepository.findByName(name);
    }

    public Cinema save(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    public void setNotVisible(Cinema cinema) {
        cinema.setVisible(false);
        cinemaRepository.save(cinema);
    }

    public List<Hall> findHallsById(UUID cinemaId){
        return hallRepository.findByCinemaId(cinemaId);
    }

}
