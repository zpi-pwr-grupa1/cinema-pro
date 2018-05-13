package pl.pwr.zpi.cinemapro.common.initialization;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
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
import pl.pwr.zpi.cinemapro.domain.employee.Employee;
import pl.pwr.zpi.cinemapro.domain.employee.EmployeeRepository;
import pl.pwr.zpi.cinemapro.domain.client.Client;
import pl.pwr.zpi.cinemapro.domain.client.ClientRepository;
import pl.pwr.zpi.cinemapro.domain.moviegroup.MovieGroup;
import pl.pwr.zpi.cinemapro.domain.moviegroup.MovieGroupRepository;

// TODO configure it to be initialized only when specific profile is used

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MovieGroupRepository groupRepository;
    
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
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    private void init() {
        
        MovieGroup g1 = new MovieGroup();
        g1.setLabel("Sci-Fi");
        
        MovieGroup g2 = new MovieGroup();
        g2.setLabel("Action");
        
        
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
        m1.setImgURL("/assets/images/movies/transformers.jpg");
        m1.setVisible(true);
        m1.setGroups(Sets.newHashSet(g1, g2));

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
        m2.setImgURL("/assets/images/movies/noposter.jpg");
        m2.setVisible(true);
        m2.setGroups(Sets.newHashSet(g1));

        Movie m3 = new Movie();
        m3.setAge("PG-12");
        m3.setCountry("USA");
        m3.setDirector("Jon Watts");
        m3.setMovieCast("Tom Holland, Michael Keaton, Robert Downey Jr., Zendaya");
        m3.setPolishReleaseDate(new Date());
        m3.setWorldReleaseDate(new Date());
        m3.setRunTime(133);
        m3.setStoryline("Peter Parker balances his life as an ordinary high school student in Queens with his superhero alter-ego Spider-Man, and finds himself on the trail of a new menace prowling the skies of New York City.");
        m3.setTitle("Spider-Man: Homecoming");
        m3.setImgURL("/assets/images/movies/spiderman.jpg");
        m3.setVisible(true);
        m3.setGroups(Sets.newHashSet(g2));

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
        
        Employee e1 = new Employee();
        cal.set(1999, 1, 1, 13, 15, 00);
        e1.setStartingDateOfEmployment(cal.getTime());
        e1.setName("Tomasz");
        e1.setSurname("Kot");
        e1.setEmail("jakis.email@email.com");
        e1.setCity("Wroclaw");
        e1.setStreet("Dluga");
        e1.setPostCode("55-555");
        e1.setStreetNumber("12");
        e1.setTelephone("777777777");
        e1.setCinema(c1);
        
        Employee e2 = new Employee();
        cal.set(2000, 1, 1, 13, 15, 00);
        e2.setStartingDateOfEmployment(cal.getTime());
        e2.setName("Anna");
        e2.setSurname("Pies");
        e2.setEmail("a.pies@email.com");
        e2.setCity("Wroclaw");
        e2.setStreet("Prosta");
        e2.setPostCode("55-535");
        e2.setStreetNumber("17/8");
        e2.setTelephone("777888777");
        e2.setCinema(c1);
        
        Client cl1 = new Client();
        cl1.setEmail("user1@email.com");
        cl1.setPassword("password1");
        cl1.setBirthDate(new Date());

        Client cl2 = new Client();
        cl2.setEmail("user2@email.com");
        cl2.setPassword("password2");
        cl2.setBirthDate(new Date());

        Client cl3 = new Client();
        cl3.setEmail("user3@email.com");
        cl3.setPassword("password3");
        cl3.setBirthDate(new Date());

        
        groupRepository.saveAll(Lists.newArrayList(g1, g2));
        clientRepository.saveAll(Lists.newArrayList(cl1, cl2, cl3));
        movieRepository.saveAll(Lists.newArrayList(m1, m2, m3));        
        cinemaRepository.saveAll(Lists.newArrayList(c1, c2, c3));
        hallRepository.saveAll(Lists.newArrayList(h1, h2, h3, h4, h5));
        showingRepository.saveAll(Lists.newArrayList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10));
        ticketTypeRepository.saveAll(Lists.newArrayList(tt1, tt2, tt3));
        ticketRepository.saveAll(Lists.newArrayList(t1, t2, t3));
        employeeRepository.saveAll(Lists.newArrayList(e1, e2));
    }

}