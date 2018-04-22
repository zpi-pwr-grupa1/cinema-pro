package pl.pwr.zpi.cinemapro.domain.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import pl.pwr.zpi.cinemapro.common.util.DTO;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Movie> getAllMovies() {
        return movieService.findAll();
    }
    
    @RequestMapping(value = "/get/all/visible", method = RequestMethod.GET)
    public List<Movie> getVisibleMovies() {
        return movieService.findAllVisible();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity registerMovie(@Valid @RequestBody @DTO(MovieForm.class) Movie movie, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        movie.setVisible(true);
        movieService.save(movie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable(value = "id") UUID id) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(movie);
    }

    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMovie(@PathVariable(value = "id") UUID id) {
            Movie movie = movieService.findById(id);
            if (movie == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            movieService.setNotVisible(movie);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DataIntegrityViolation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void constraintViolation(){
    }
}