package pl.pwr.zpi.cinemapro.domain.cinema;

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
    public Cinema getById(@PathVariable(value = "id") UUID id) {
        return cinemaService.findByID(id);
    }
    @RequestMapping(value = "get/{name}", method = RequestMethod.GET)
    public Cinema getByName(@PathVariable(value = "name") String name) {
        return cinemaService.findByName(name);
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
        Cinema cinema = cinemaService.findByID(id);
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