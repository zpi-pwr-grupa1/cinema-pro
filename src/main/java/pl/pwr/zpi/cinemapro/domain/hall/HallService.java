package pl.pwr.zpi.cinemapro.domain.hall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HallService {

    @Autowired
    HallRepository hallRepository;

    public List<Hall> findAll() {
        return hallRepository.findAll();
    }

    public List<Hall> findAllVisible() {
        return hallRepository.findByVisible(true);
    }

    public Hall findById(UUID id){
        return hallRepository.findById(id);
    }


    public Hall save(Hall hall) {
        return hallRepository.save(hall);
    }

    public void setNotVisible(Hall hall) {
        hall.setVisible(false);
        hallRepository.save(hall);
    }

}
