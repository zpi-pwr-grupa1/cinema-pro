package pl.pwr.zpi.cinemapro.domain.seat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.pwr.zpi.cinemapro.common.util.DTO;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/seat")
public class SeatController {

    @Autowired
    SeatService seatService;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Seat> getAllSeats() {
        return seatService.findAll();
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/get/all/visible", method = RequestMethod.GET)
    public List<Seat> getVisibleSeats() {
        return seatService.findAllVisible();
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable(value = "id") UUID id) {
        Seat seat = seatService.findById(id);
        if (seat == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(seat);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity registerOrUpdateSeat(@Valid @RequestBody @DTO(SeatForm.class) Seat seat, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        seat.setVisible(true);
        seatService.save(seat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSeat(@PathVariable(value = "id") UUID id) {
        Seat seat = seatService.findById(id);
        if (seat == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        seatService.setNotVisible(seat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DataIntegrityViolation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void constraintViolation() {

    }
}