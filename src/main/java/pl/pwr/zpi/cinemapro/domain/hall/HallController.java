package pl.pwr.zpi.cinemapro.domain.hall;

import pl.pwr.zpi.cinemapro.domain.hall.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.pwr.zpi.cinemapro.common.util.DTO;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hall")
public class HallController {

    @Autowired
    HallService hallService;

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Hall> getAllhalls() {
        return hallService.findAll();
    }

    @RequestMapping(value = "/get/all/visible", method = RequestMethod.GET)
    public List<Hall> getVisiblehalls() {
        return hallService.findAllVisible();
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable(value = "id") UUID id) {
        Hall hall = hallService.findById(id);
        if (hall == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(hall);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity registerOrUpdatehall(@Valid @RequestBody @DTO(HallForm.class) Hall hall, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        hallService.save(hall);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletehall(@PathVariable(value = "id") UUID id) {
        Hall hall = hallService.findById(id);
        if (hall == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        hallService.setNotVisible(hall);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DataIntegrityViolation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void constraintViolation() {

    }
}