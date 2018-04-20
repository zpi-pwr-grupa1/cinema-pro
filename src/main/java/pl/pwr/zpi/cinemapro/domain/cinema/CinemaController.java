package pl.pwr.zpi.cinemapro.domain.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.pwr.zpi.cinemapro.common.util.DTO;
import pl.pwr.zpi.cinemapro.domain.hall.Hall;
import pl.pwr.zpi.cinemapro.domain.showing.Showing;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/cinema")
public class CinemaController {

    @Autowired
    CinemaService cinemaService;

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Cinema> getAllCinemas() {
        return cinemaService.findAll();
    }

    @RequestMapping(value = "/get/all/visible", method = RequestMethod.GET)
    public List<Cinema> getVisibleCinemas() {
        return cinemaService.findAllVisible();
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable(value = "id") UUID id) {
        Cinema cinema = cinemaService.findById(id);
        if (cinema == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cinema);
    }
    @RequestMapping(value = "get/{id}/halls", method = RequestMethod.GET)
    public ResponseEntity getHalls(@PathVariable(value = "id") UUID id) {
        List<Hall> halls = cinemaService.findHallsById(id);
        if (halls == null || halls.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(halls);
    }
    @RequestMapping(value = "get/{id}/halls/visible", method = RequestMethod.GET)
    public ResponseEntity getVisibleHalls(@PathVariable(value = "id") UUID id) {
        List<Hall> halls = cinemaService.findVisibleHallsById(id);
        if (halls == null || halls.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(halls);
    }

    @RequestMapping(value = "get/{id}/showings", method = RequestMethod.GET)
    public ResponseEntity getShowings(@PathVariable(value = "id") UUID id) {
        List<Showing> showings = cinemaService.findShowingsById(id);
        if (showings == null || showings.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(showings);
    }
    @RequestMapping(value = "get/{id}/showings/planned", method = RequestMethod.GET)
    public ResponseEntity getPlannedShowings(@PathVariable(value = "id") UUID id) {
        List<Showing> showings = cinemaService.findPlannedShowingsById(id);
        if (showings == null || showings.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(showings);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity registerOrUpdateCinema(@Valid @RequestBody @DTO(CinemaForm.class) Cinema cinema, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        cinemaService.save(cinema);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCinema(@PathVariable(value = "id") UUID id) {
        Cinema cinema = cinemaService.findById(id);
        if (cinema == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        cinemaService.setNotVisible(cinema);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DataIntegrityViolation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void constraintViolation() {

    }
}