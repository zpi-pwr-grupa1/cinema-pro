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

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity registerCinema(@Valid @RequestBody @DTO(CinemaForm.class) Cinema cinema, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
            cinemaService.save(cinema);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DataIntegrityViolation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void constraintViolation(){
    }
}