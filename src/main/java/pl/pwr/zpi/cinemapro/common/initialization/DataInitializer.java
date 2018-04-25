package pl.pwr.zpi.cinemapro.common.initialization;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.pwr.zpi.cinemapro.domain.cinema.Cinema;
import pl.pwr.zpi.cinemapro.domain.cinema.CinemaRepository;
import pl.pwr.zpi.cinemapro.domain.hall.Hall;
import pl.pwr.zpi.cinemapro.domain.hall.HallRepository;
import pl.pwr.zpi.cinemapro.domain.movie.Movie;
import pl.pwr.zpi.cinemapro.domain.movie.MovieRepository;
import pl.pwr.zpi.cinemapro.domain.seat.Seat;
import pl.pwr.zpi.cinemapro.domain.seat.SeatService;
import pl.pwr.zpi.cinemapro.domain.showing.Showing;
import pl.pwr.zpi.cinemapro.domain.showing.ShowingRepository;
import pl.pwr.zpi.cinemapro.domain.ticket.Ticket;
import pl.pwr.zpi.cinemapro.domain.ticket.TicketRepository;
import pl.pwr.zpi.cinemapro.domain.ticket.type.TicketType;
import pl.pwr.zpi.cinemapro.domain.ticket.type.TicketTypeRepository;

import java.util.*;

// TODO configure it to be initialized only when specific profile is used

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowingRepository showingRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private SeatService seatService;



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    private void init() {
        TicketType tt1 = new TicketType();
        tt1.setName("Normalny");
        tt1.setPrice(20.0);
        tt1.setVisible(true);

        TicketType tt2 = new TicketType();
        tt2.setName("Ulgowy");
        tt2.setPrice(15.0);
        tt2.setVisible(true);

        TicketType tt3 = new TicketType();
        tt3.setName("Studencki");
        tt3.setPrice(10.0);
        tt3.setVisible(true);

        Movie m1 = new Movie();
        m1.setAge("PG-13");
        m1.setCountry("USA");
        m1.setDirector("Michael Bay");
        m1.setMovieCast("Shia LaBeouf, Megan Fox, Josh Duhamel");
        m1.setPolishReleaseDate(new Date());
        m1.setWorldReleaseDate(new Date());
        m1.setRunTime(144);
        m1.setStoryline("Something with robots and EXPLOSIONSSSS!!!");
        m1.setTitle("Transformers");
        m1.setVisible(true);

        Movie m2 = new Movie();
        m2.setAge("PG-18");
        m2.setCountry("USA");
        m2.setDirector("Michael Boy");
        m2.setMovieCast("Testing Tost, Hamster Dumpster");
        m2.setPolishReleaseDate(new Date());
        m2.setWorldReleaseDate(new Date());
        m2.setRunTime(120);
        m2.setStoryline("Something with hamsters and DUMPSTERS!!!");
        m2.setTitle("Hamstermers");
        m2.setVisible(true);

        Movie m3 = new Movie();
        m3.setAge("PG-13");
        m3.setCountry("GB");
        m3.setDirector("John Watson");
        m3.setMovieCast("Scherlock Holmes, Doctor Moriarty");
        m3.setPolishReleaseDate(new Date());
        m3.setWorldReleaseDate(new Date());
        m3.setRunTime(120);
        m3.setStoryline("Tale of fascinating intrigue.");
        m3.setTitle("Holmriarty");
        m3.setVisible(true);

        Cinema c1 = new Cinema();
        c1.setName("CinemaPro Grabiszynska");
        c1.setStreet("Grabiszynska");
        c1.setStreetNumber("10");
        c1.setPostCode("11-111");
        c1.setCity("Wroclaw");
        c1.setDescription("Jakis opis");
        c1.setTelephone("710000000");
        c1.setEmail("email@email.com");
        c1.setImgUrl("http://www.marcustheatres.com/media/images/gallery-images/ridge-cinema-new-berlin/47-ridge-exteriorjpg.jpg");
        c1.setVisible(true);

        Cinema c2 = new Cinema();
        c2.setName("CinemaPro Olawska");
        c2.setStreet("Olawska");
        c2.setStreetNumber("12");
        c2.setPostCode("12-123");
        c2.setCity("Wroclaw");
        c2.setDescription("Jakis opis dobrze opisujacy");
        c2.setTelephone("710010000");
        c2.setEmail("mail@email.com");
        c2.setImgUrl("https://i.pinimg.com/originals/72/2d/90/722d90835c5907ee5e0155e2d73633d6.jpg");
        c2.setVisible(true);

        Cinema c3 = new Cinema();
        c3.setName("CinemaPro Legnicka");
        c3.setStreet("Legnicka");
        c3.setStreetNumber("15");
        c3.setPostCode("11-222");
        c3.setCity("Wroclaw");
        c3.setDescription("Test description");
        c3.setTelephone("71111222");
        c3.setEmail("email2@email.com");
        c3.setImgUrl("https://i.ytimg.com/vi/PjHumS-GVAo/hqdefault.jpg");
        c3.setVisible(true);

        Hall h1 = new Hall();
        h1.setHallNumber(1);
        HashSet<Seat> seats = new HashSet<>(seatService.createSeats(10, 10));
        h1.setSeats(seats);
        h1.setCinema(c1);
        h1.setVisible(true);

        Hall h2 = new Hall();
        h2.setHallNumber(2);
        HashSet<Seat> seats2 = new HashSet<>(seatService.createSeats(20, 15));
        h2.setSeats(seats2);
        h2.setCinema(c1);
        h2.setVisible(true);

        Hall h3 = new Hall();
        h3.setHallNumber(1);
        HashSet<Seat> seats3 = new HashSet<>(seatService.createSeats(15, 20));
        h3.setSeats(seats3);
        h3.setCinema(c2);
        h3.setVisible(true);

        Hall h4 = new Hall();
        h4.setHallNumber(2);
        HashSet<Seat> seats4 = new HashSet<>(seatService.createSeats(14, 13));
        h4.setSeats(seats4);
        h4.setCinema(c2);
        h4.setVisible(true);

        Hall h5 = new Hall();
        h5.setHallNumber(12);
        HashSet<Seat> seats5 = new HashSet<>(seatService.createSeats(14, 13));
        h5.setSeats(seats5);
        h5.setCinema(c3);
        h5.setVisible(true);

        Ticket t1 = new Ticket();
        Seat seat = seats.iterator().next();
        t1.setSeat(seat);
        t1.setTicketType(tt1);

        Ticket t2 = new Ticket();
        seat = seats.iterator().next();
        t2.setSeat(seat);
        t2.setTicketType(tt2);

        Ticket t3 = new Ticket();
        seat = seats.iterator().next();
        t3.setSeat(seat);
        t3.setTicketType(tt3);

        Showing s1 = new Showing();
        s1.setScreeningStart(new Date());
        s1.setHall(h1);
        s1.setMovie(m1);

        Calendar cal = Calendar.getInstance();

        Showing s2 = new Showing();
        cal.set(2053, 1, 1, 15, 15, 00);
        s2.setScreeningStart(cal.getTime());
        s2.setHall(h2);
        s2.setMovie(m2);

        Showing s5 = new Showing();
        cal.set(2053, 1, 1, 17, 15, 00);
        s5.setScreeningStart(cal.getTime());
        s5.setHall(h2);
        s5.setMovie(m2);

        Showing s3 = new Showing();
        cal.set(2053, 1, 1, 15, 30, 00);
        s3.setScreeningStart(cal.getTime());
        s3.setHall(h1);
        s3.setMovie(m3);

        Showing s4 = new Showing();
        cal.set(2053, 1, 1, 10, 15, 00);
        s4.setScreeningStart(cal.getTime());
        s4.setHall(h2);
        s4.setMovie(m3);

        Showing s6 = new Showing();
        cal.set(2053, 1, 1, 15, 15, 00);
        s6.setScreeningStart(cal.getTime());
        s6.setHall(h3);
        s6.setMovie(m1);

        Showing s7 = new Showing();
        cal.set(2053, 1, 1, 17, 15, 00);
        s7.setScreeningStart(cal.getTime());
        s7.setHall(h3);
        s7.setMovie(m2);

        Showing s8 = new Showing();
        cal.set(2053, 1, 1, 15, 30, 00);
        s8.setScreeningStart(cal.getTime());
        s8.setHall(h4);
        s8.setMovie(m3);

        Showing s9 = new Showing();
        cal.set(2053, 1, 1, 13, 15, 00);
        s9.setScreeningStart(cal.getTime());
        s9.setHall(h4);
        s9.setMovie(m3);

        Showing s10 = new Showing();
        cal.set(2053, 1, 1, 13, 15, 00);
        s10.setScreeningStart(cal.getTime());
        s10.setHall(h5);
        s10.setMovie(m1);


        movieRepository.saveAll(Lists.newArrayList(m1, m2, m3));
        cinemaRepository.saveAll(Lists.newArrayList(c1, c2, c3));
        hallRepository.saveAll(Lists.newArrayList(h1, h2, h3, h4, h5));
        showingRepository.saveAll(Lists.newArrayList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10));
        ticketTypeRepository.saveAll(Lists.newArrayList(tt1, tt2, tt3));
        ticketRepository.saveAll(Lists.newArrayList(t1, t2, t3));
    }

}