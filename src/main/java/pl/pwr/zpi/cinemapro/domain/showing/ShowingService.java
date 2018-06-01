package pl.pwr.zpi.cinemapro.domain.showing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.zpi.cinemapro.domain.reservation.Reservation;
import pl.pwr.zpi.cinemapro.domain.reservation.ReservationRepository;
import pl.pwr.zpi.cinemapro.domain.reservation.ReservationService;
import pl.pwr.zpi.cinemapro.domain.seat.Seat;
import pl.pwr.zpi.cinemapro.domain.seat.ShowingSeatForm;
import pl.pwr.zpi.cinemapro.domain.ticket.Ticket;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShowingService {

    @Autowired
    ShowingRepository showingRepository;

    @Autowired
    ReservationService reservationService;

    public List<Showing> findAll() {
        return showingRepository.findAll();
    }

    public List<Showing> findAfter(Date date) {
        return showingRepository.findByScreeningStartAfter(date);
    }

    public Showing findById(UUID id) {
        return showingRepository.findById(id);
    }

    public List<Seat> findTakenSeats(Showing showing) {
        List<Reservation> reservations = reservationService.findByShowingId(showing.getId());
        List<Seat> seats = reservations.stream()
                .flatMap(r -> r.getTickets().stream())
                .map(t -> t.getSeat())
                .collect(Collectors.toList());
        return seats;
    }

    public List<ShowingSeatForm> getSeats(Showing showing) {
        Set<Seat> seats = showing.getHall().getSeats();
        List<Reservation> reservations = reservationService.findByShowingId(showing.getId());
        List<Seat> takenSeats = reservations.stream()
                .flatMap(r -> r.getTickets().stream())
                .map(t -> t.getSeat())
                .collect(Collectors.toList());

        List<ShowingSeatForm> showingSeatForms = new LinkedList<>();

        for (Seat seat : seats) {
            boolean taken = takenSeats.stream().anyMatch(s -> s.getId().equals(seat.getId()));
            ShowingSeatForm showingSeatForm = new ShowingSeatForm(seat.getId(), seat.getSeatRow(), seat.getSeatColumn(), taken);
            showingSeatForms.add(showingSeatForm);
        }

        Comparator<ShowingSeatForm> comparator = Comparator.comparing(ShowingSeatForm::getSeatColumn).thenComparing(ShowingSeatForm::getSeatRow);
        showingSeatForms = showingSeatForms.stream().sorted(comparator).collect(Collectors.toList());
        return showingSeatForms;
    }

    public Showing save(Showing showing) {
        return showingRepository.save(showing);
    }

    public void delete(Showing showing) {
        showingRepository.delete(showing);
    }

    public boolean existsOverlappingShowing(Showing showing){
        Date currentDate = new Date();
        List<Showing> showings = findAfter(currentDate);
        showings.stream().filter(s-> s.getHall().getId().equals(showing.getHall().getId()));
        for(Showing s : showings){
            if(checkIfOverlap(s, showing)){
                return true;
            }
        }
        return false;
    }

    public boolean checkIfOverlap(Showing s1, Showing s2) {
        Date startDate1 = s1.getScreeningStart();
        Date endDate1 = getScreeningEnd(s1);
        Date startDate2 = s2.getScreeningStart();
        Date endDate2 = getScreeningEnd(s2);
        boolean result = startDate1.before(endDate2) && startDate2.before(endDate1);
        return result;
    }

    public Date getScreeningEnd(Showing showing) {
        final long ONE_MINUTE_IN_MILIS = 60000;

        int movieLength = showing.getMovie().getRunTime();
        long screeningStart = showing.getScreeningStart().getTime();
        Date screeningEnd = new Date(screeningStart + (movieLength * ONE_MINUTE_IN_MILIS));
        return  screeningEnd;
    }
}
