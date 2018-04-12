package pl.pwr.zpi.cinemapro.domain.showing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShowingService {

    @Autowired
    ShowingRepository showingRepository;

    public List<Showing> findAll() {
        return showingRepository.findAll();
    }

    public Showing findById(UUID id) {
        return showingRepository.findById(id);
    }

    public Showing save(Showing showing) {
        return showingRepository.save(showing);
    }
}
