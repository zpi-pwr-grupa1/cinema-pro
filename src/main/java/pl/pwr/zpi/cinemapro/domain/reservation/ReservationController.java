package pl.pwr.zpi.cinemapro.domain.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.pwr.zpi.cinemapro.common.util.DTO;
import pl.pwr.zpi.cinemapro.domain.client.Client;
import pl.pwr.zpi.cinemapro.domain.client.ClientService;
import pl.pwr.zpi.cinemapro.domain.showing.Showing;
import pl.pwr.zpi.cinemapro.domain.showing.ShowingService;
import pl.pwr.zpi.cinemapro.domain.ticket.Ticket;
import pl.pwr.zpi.cinemapro.domain.ticket.TicketService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ClientService clientService;

    @Autowired
    ShowingService showingService;

    @Autowired
    TicketService ticketService;

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Reservation> getAllReservations() {
        return reservationService.findAll();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity registerReservation(@Valid @RequestBody @DTO(ReservationForm.class) Reservation reservationForm,
                                              BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Client client = clientService.findById(reservationForm.getClient().getId());
        Showing showing = showingService.findById(reservationForm.getShowing().getId());
        Set<Ticket> tickets = new HashSet<>();
        for (Ticket t : reservationForm.getTickets()) {
            tickets.add(ticketService.findById(t.getId()));
        }
        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setShowing(showing);
        reservation.setTickets(tickets);

        reservationService.save(reservation);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}/tickets", method = RequestMethod.GET)
    public ResponseEntity getAllTickets(@PathVariable(value = "id") UUID id){
        Set<Ticket> tickets = reservationService.findTicketsById(id);
        if (tickets == null || tickets.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(tickets);
    }
    
    @RequestMapping(value = "/buy/{id}/all", method = RequestMethod.POST)
    public ResponseEntity buyAllTicketsFromReservation(@PathVariable(value = "id") UUID id){
        Set<Ticket> tickets = reservationService.findTicketsById(id);
        if (tickets == null || tickets.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        double sum = 0.0;
        sum = tickets.stream().map((t) -> {
            t.setPrice(t.getTicketType().getPrice());
            return t;
        }).map((t) -> t.getPrice()).reduce(sum, (accumulator, _item) -> accumulator + _item);
        return ResponseEntity.ok(sum);
    }
}
