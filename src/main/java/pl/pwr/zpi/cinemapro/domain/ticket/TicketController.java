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
    public ResponseEntity deleteTicket(@PathVariable(value = "id") UUID id) {
        Ticket ticket = ticketService.findById(id);
        if (ticket == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ticketService.delete(ticket);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/isbought/{id}", method = RequestMethod.GET)
    public ResponseEntity isBoughtTicketByID(@PathVariable(value = "id") UUID id) {
        Ticket ticket = ticketService.findById(id);
        if (ticket == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (ticket.isPaid() == false){
            return ResponseEntity.ok("false");
        }
        return ResponseEntity.ok("true");
    }
    
    @RequestMapping(value = "/buy/{id}", method = RequestMethod.POST)
    public ResponseEntity buyTicketByID(@PathVariable(value = "id") UUID id) {
        Ticket ticket = ticketService.findById(id);
        if (ticket == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (ticket.isPaid() == false){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        ticket.setPrice(ticket.getTicketType().getPrice());
        return ResponseEntity.ok(ticket);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DataIntegrityViolation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void constraintViolation(){
    }
}