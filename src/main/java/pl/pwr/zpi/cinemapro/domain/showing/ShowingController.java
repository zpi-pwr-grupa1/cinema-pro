package pl.pwr.zpi.cinemapro.domain.showing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import pl.pwr.zpi.cinemapro.common.util.DTO;
import pl.pwr.zpi.cinemapro.domain.seat.Seat;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/showing")
public class ShowingController {

    @Autowired
    ShowingService showingService;

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Showing> getAllShowings() {
        return showingService.findAll();
    }

    @RequestMapping(value="/get/all/planned", method = RequestMethod.GET)
    public List<Showing> getAllPlanned(){
        Date currentDate = new Date();
        return showingService.findAfter(currentDate);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable(value = "id") UUID id) {
        Showing showing = showingService.findById(id);
        if (showing == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(showing);
    }

    @RequestMapping(value = "/get/{id}/seats/taken", method = RequestMethod.GET)
    public ResponseEntity getReservedSeats(@PathVariable(value = "id") UUID id) {
        Showing showing = showingService.findById(id);
        if (showing == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        List<Seat> seats = showingService.findTakenSeats(showing);
        return ResponseEntity.ok(seats);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteShowing(@PathVariable(value = "id") UUID id) {
        Showing showing = showingService.findById(id);
        if (showing == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        showingService.delete(showing);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity addShowing(@Valid @RequestBody @DTO(ShowingForm.class) Showing showing, BindingResult result) {
        if (result.hasErrors() || showing.getId() == null && showingService.existsOverlappingShowing(showing)){
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        showingService.save(showing);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DataIntegrityViolation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void constraintViolation(){
    }
}