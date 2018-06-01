package pl.pwr.zpi.cinemapro.domain.hall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.pwr.zpi.cinemapro.common.util.DTO;
import pl.pwr.zpi.cinemapro.domain.seat.Seat;
import pl.pwr.zpi.cinemapro.domain.seat.SeatService;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/hall")
public class HallController {

    @Autowired
    HallService hallService;

    @Autowired
    SeatService seatsService;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Hall> getAllhalls() {
        return hallService.findAll();
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/get/all/visible", method = RequestMethod.GET)
    public List<Hall> getVisiblehalls() {
        return hallService.findAllVisible();
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable(value = "id") UUID id) {
        Hall hall = hallService.findById(id);
        if (hall == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(hall);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "get/{id}/columnsandrows", method = RequestMethod.GET)
    public ResponseEntity getColumnsAndRows(@PathVariable(value = "id") UUID id) {
        Hall hall = hallService.findById(id);
        if (hall == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Set<Seat> seats = hall.getSeats();

        int rowsCount = seats.stream().map(s -> s.getSeatRow()).max(Comparator.naturalOrder()).get() + 1;
        int columnsCount = seats.stream().map(s -> s.getSeatColumn()).max(Comparator.naturalOrder()).get() + 1;
        ColumnsRowsForm columnsRowsForm = new ColumnsRowsForm(rowsCount, columnsCount);
        return new ResponseEntity<>(columnsRowsForm, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity registerOrUpdatehall(@Valid @RequestBody @DTO(HallForm.class) Hall hall, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        hall.setVisible(true);
        hallService.save(hall);
        return new ResponseEntity<>(hall.getId(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletehall(@PathVariable(value = "id") UUID id) {
        Hall hall = hallService.findById(id);
        if (hall == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        hallService.setNotVisible(hall);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(
            value = "/createseats",
            params = { "id" , "rows", "columns"},
            method = RequestMethod.GET)
    public ResponseEntity createMultipleSeats(@RequestParam("id") UUID id, @RequestParam("rows") int rows, @RequestParam("columns") int columns){
        Hall hall = hallService.findById(id);
        if (hall == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (hall.getSeats() == null){
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        Set<Seat> seats = new HashSet<>(seatsService.createSeats(columns, rows));
        hall.setSeats(seats);
        hallService.save(hall);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DataIntegrityViolation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void constraintViolation() {
    }
}