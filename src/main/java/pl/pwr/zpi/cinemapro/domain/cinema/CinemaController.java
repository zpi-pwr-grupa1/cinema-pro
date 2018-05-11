package pl.pwr.zpi.cinemapro.domain.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.pwr.zpi.cinemapro.common.util.DTO;
import pl.pwr.zpi.cinemapro.domain.hall.Hall;
import pl.pwr.zpi.cinemapro.domain.hall.HallForm;
import pl.pwr.zpi.cinemapro.domain.hall.HallService;
import pl.pwr.zpi.cinemapro.domain.showing.Showing;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cinema")
public class CinemaController {

    @Autowired
    CinemaService cinemaService;

    @Autowired
    HallService hallService;

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
        if (showings == null) {
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

    @RequestMapping(value = "get/{id}/showings/{date}", method = RequestMethod.GET)
    public ResponseEntity getShowingsOnDay(@PathVariable(value = "id") UUID cinemaId,
                                           @PathVariable(value= "date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        List<Showing> showings = cinemaService.findShowingsOnDate(cinemaId, date);
        if (showings == null || showings.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(showings);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity registerCinema(@Valid @RequestBody @DTO(CinemaForm.class) Cinema cinema, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        cinema.setVisible(true);
        cinemaService.save(cinema);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/modify", method = RequestMethod.PUT)
    public ResponseEntity updateCinema(@Valid @RequestBody @DTO(CinemaForm.class) Cinema cinema, BindingResult result) {
        Cinema existingCinema = cinemaService.findById(cinema.getId());
        if (result.hasErrors() || existingCinema == null) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        existingCinema.setName(cinema.getName());
        existingCinema.setStreet(cinema.getStreet());
        existingCinema.setStreetNumber(cinema.getStreetNumber());
        existingCinema.setPostCode(cinema.getPostCode());
        existingCinema.setCity(cinema.getCity());
        existingCinema.setTelephone(cinema.getTelephone());
        existingCinema.setDescription(cinema.getDescription());
        existingCinema.setEmail(cinema.getEmail());
        existingCinema.setImgUrl(cinema.getImgUrl());
        cinemaService.save(existingCinema);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/put/hall", method = RequestMethod.PUT)
    public ResponseEntity addHallToCinema(@Valid @RequestBody @DTO(HallForm.class) Hall hall,
                                  @PathVariable(value ="id") UUID cinemaId, BindingResult result) {
        Cinema cinema = cinemaService.findById(cinemaId);
        hall.setCinema(cinema);
        hall.setVisible(true);
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        cinemaService.save(cinema);
        hallService.save(hall);
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