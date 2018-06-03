package pl.pwr.zpi.cinemapro.domain.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.pwr.zpi.cinemapro.common.util.DTO;
import pl.pwr.zpi.cinemapro.domain.client.Client;
import pl.pwr.zpi.cinemapro.domain.client.ClientService;
import pl.pwr.zpi.cinemapro.domain.seat.Seat;
import pl.pwr.zpi.cinemapro.domain.seat.SeatService;
import pl.pwr.zpi.cinemapro.domain.showing.Showing;
import pl.pwr.zpi.cinemapro.domain.showing.ShowingService;
import pl.pwr.zpi.cinemapro.domain.ticket.AlternativeTicketForm;
import pl.pwr.zpi.cinemapro.domain.ticket.Ticket;
import pl.pwr.zpi.cinemapro.domain.ticket.TicketService;
import pl.pwr.zpi.cinemapro.domain.ticket.type.TicketType;
import pl.pwr.zpi.cinemapro.domain.ticket.type.TicketTypeService;
import pl.pwr.zpi.cinemapro.domain.user.User;
import pl.pwr.zpi.cinemapro.domain.user.UserRepository;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@RestController
@RequestMapping("/api/reservation")
@PreAuthorize("permitAll()")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ClientService clientService;

    @Autowired
    ShowingService showingService;

    @Autowired
    TicketService ticketService;

    @Autowired
    TicketTypeService ticketTypeService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SeatService seatService;

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Reservation> getAllReservations() {
        return reservationService.findAll();
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity bookReservation(@Valid @RequestBody AlternativeReservationForm reservationForm, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());
        Client client = clientService.findById(user.getId());

        Showing showing = showingService.findById(reservationForm.getShowingId());

        Set<Ticket> tickets = new HashSet<>();
        for (AlternativeTicketForm tf : reservationForm.getReservations()) {
            Seat seat = seatService.findById(tf.getSeatId());
            TicketType ticketType = ticketTypeService.findById(tf.getTicketTypeId());
            Ticket ticket = new Ticket();
            ticket.setSeat(seat);
            ticket.setTicketType(ticketType);
            ticket.setPrice(ticketType.getPrice());
            ticket.setPaid(false);
            ticketService.save(ticket);
            tickets.add(ticket);
        }

        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setShowing(showing);
        reservation.setTickets(tickets);
        reservationService.save(reservation);
        return new ResponseEntity(HttpStatus.OK);
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
