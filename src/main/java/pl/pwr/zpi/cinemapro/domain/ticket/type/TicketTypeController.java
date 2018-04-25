package pl.pwr.zpi.cinemapro.domain.ticket.type;

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
@RequestMapping("/api/ticket/type")
public class TicketTypeController {

    @Autowired
    TicketTypeService ticketTypeService;

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<TicketType> getAllTicketTypes() {
        return ticketTypeService.findAll();
    }
    @RequestMapping(value = "/get/all/visible", method = RequestMethod.GET)
    public List<TicketType> getAllVisibleTicketTypes() {
        return ticketTypeService.findAllVisible();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity getTicketTypeByID(@PathVariable(value = "id") UUID id) {
        TicketType ticketType = ticketTypeService.findById(id);
        if (ticketType == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(ticketType);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity addTicketType(@Valid @RequestBody @DTO(TicketTypeForm.class) TicketType ticketType, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        ticketType.setVisible(true);
        ticketTypeService.save(ticketType);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTicketType(@PathVariable(value = "id") UUID id) {
        TicketType ticketType = ticketTypeService.findById(id);
        if (ticketType == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ticketTypeService.setNotVisible(ticketType);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DataIntegrityViolation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void constraintViolation(){
    }
}