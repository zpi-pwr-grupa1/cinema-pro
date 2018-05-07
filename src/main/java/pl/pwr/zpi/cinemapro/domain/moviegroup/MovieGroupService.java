package pl.pwr.zpi.cinemapro.domain.moviegroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieGroupService {

    @Autowired
    MovieGroupRepository movieGroupRepository;

    public List<MovieGroup> findAll() {
        return movieGroupRepository.findAll();
    }

    public MovieGroup findById(UUID id){
        return movieGroupRepository.findById(id);
    }

    public List<MovieGroup> findAllVisible() {
        return movieGroupRepository.findByVisible(true);
    }
    
    public void setNotVisible(MovieGroup movieGroup) {
        movieGroup.setVisible(false);
        movieGroupRepository.save(movieGroup);
    }

    public MovieGroup save(MovieGroup moviegroup) {
        return movieGroupRepository.save(moviegroup);
    }

    public void delete(MovieGroup moviegroup) {
        movieGroupRepository.delete(moviegroup);
    }

}
