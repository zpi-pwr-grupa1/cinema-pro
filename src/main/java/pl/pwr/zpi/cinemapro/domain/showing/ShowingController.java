package pl.pwr.zpi.cinemapro.domain.showing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import pl.pwr.zpi.cinemapro.common.util.DTO;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/showing")
public class ShowingController {

    @Autowired
    ShowingService showingService;

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Showing> getAllClients() {
        return showingService.findAll();
    }

    @RequestMapping(value="/get/all/planned", method = RequestMethod.GET)
    public List<Showing> getAllPlanned(){
        Date currentDate = new Date();
        return showingService.findAfter(currentDate);
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
    public ResponseEntity registerClient(@Valid @RequestBody @DTO(ShowingForm.class) Showing showing, BindingResult result) {
        if (result.hasErrors() || showing.getId() == null && existsOverlappingShowing(showing)){
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        showingService.save(showing);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    private boolean existsOverlappingShowing(Showing showing){
        List<Showing> showings = getAllPlanned();
        showings.stream().filter(s-> s.getHall().equals(showing.getHall().getId()));
        for(Showing s : showings){
            if(checkIfOverlap(s, showing)){
                return true;
            }
        }
        return false;
    }

    private boolean checkIfOverlap(Showing s1, Showing s2) {
        Date startDate1 = s1.getScreeningStart();
        Date endDate1 = getScreeningEnd(s1);
        Date startDate2 = s2.getScreeningStart();
        Date endDate2 = getScreeningEnd(s2);
        boolean result = startDate1.before(endDate2) && startDate2.before(endDate1);
        return result;
    }

    private Date getScreeningEnd(Showing showing) {
        final long ONE_MINUTE_IN_MILIS = 60000;

        int movieLength = showing.getMovie().getRunTime();
        long screeningStart = showing.getScreeningStart().getTime();
        Date screeningEnd = new Date(screeningStart + (movieLength * ONE_MINUTE_IN_MILIS));
        return  screeningEnd;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DataIntegrityViolation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void constraintViolation(){
    }
}