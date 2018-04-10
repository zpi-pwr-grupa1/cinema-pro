package pl.pwr.zpi.cinemapro.domain.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
    
    public List<Movie> findAllVisible() {
        return movieRepository.findByVisible(true);
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }
    
    public void setNotVisible(Movie movie) {
        movie.setVisible(false);
        movieRepository.save(movie);
    }

    public Movie findById(UUID id){
        return movieRepository.findById(id);
    }
}
