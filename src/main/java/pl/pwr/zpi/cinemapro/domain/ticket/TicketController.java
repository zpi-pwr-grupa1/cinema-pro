package pl.pwr.zpi.cinemapro.domain.ticket;

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
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Ticket> getAllTickets() {
        return ticketService.findAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity getTicketByID(@PathVariable(value = "id") UUID id) {
        Ticket ticket = ticketService.findById(id);
        if (ticket == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(ticket);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity addTicket(@Valid @RequestBody @DTO(TicketForm.class) Ticket ticket, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        ticketService.save(ticket);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteShowing(@PathVariable(value = "id") UUID id) {
        Ticket ticket = ticketService.findById(id);
        if (ticket == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ticketService.delete(ticket);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DataIntegrityViolation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void constraintViolation(){
    }
}