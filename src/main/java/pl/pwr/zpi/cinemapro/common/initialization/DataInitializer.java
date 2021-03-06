package pl.pwr.zpi.cinemapro.common.initialization;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.pwr.zpi.cinemapro.domain.admin.Admin;
import pl.pwr.zpi.cinemapro.domain.admin.AdminRepository;
import pl.pwr.zpi.cinemapro.domain.cinema.Cinema;
import pl.pwr.zpi.cinemapro.domain.cinema.CinemaRepository;
import pl.pwr.zpi.cinemapro.domain.client.Client;
import pl.pwr.zpi.cinemapro.domain.client.ClientRepository;
import pl.pwr.zpi.cinemapro.domain.employee.Employee;
import pl.pwr.zpi.cinemapro.domain.employee.EmployeeRepository;
import pl.pwr.zpi.cinemapro.domain.hall.Hall;
import pl.pwr.zpi.cinemapro.domain.hall.HallRepository;
import pl.pwr.zpi.cinemapro.domain.movie.Movie;
import pl.pwr.zpi.cinemapro.domain.movie.MovieRepository;
import pl.pwr.zpi.cinemapro.domain.moviegroup.MovieGroup;
import pl.pwr.zpi.cinemapro.domain.moviegroup.MovieGroupRepository;
import pl.pwr.zpi.cinemapro.domain.reservation.Reservation;
import pl.pwr.zpi.cinemapro.domain.reservation.ReservationRepository;
import pl.pwr.zpi.cinemapro.domain.seat.Seat;
import pl.pwr.zpi.cinemapro.domain.seat.SeatService;
import pl.pwr.zpi.cinemapro.domain.showing.Showing;
import pl.pwr.zpi.cinemapro.domain.showing.ShowingRepository;
import pl.pwr.zpi.cinemapro.domain.ticket.Ticket;
import pl.pwr.zpi.cinemapro.domain.ticket.TicketRepository;
import pl.pwr.zpi.cinemapro.domain.ticket.type.TicketType;
import pl.pwr.zpi.cinemapro.domain.ticket.type.TicketTypeRepository;

import java.util.*;

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
    private AdminRepository adminRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

        Movie m4 = new Movie();
        m4.setAge("PG-18");
        m4.setCountry("USA");
        m4.setDirector("Anthony Russo, Joe Russo");
        m4.setMovieCast("Tom Holland, Chris Hemsworth, Robert Downey Jr., Mark Ruffalo, Chris Evans");
        m4.setPolishReleaseDate(new Date());
        m4.setWorldReleaseDate(new Date());
        m4.setRunTime(133);
        m4.setStoryline("Having acquired the Power Stone from the planet Xandar, Thanos and his lieutenants—Ebony Maw, Cull Obsidian, Proxima Midnight, and Corvus Glaive—intercept the spaceship carrying the survivors of Asgard's destruction. ");
        m4.setTitle("Avengers: Infinity War");
        m4.setImgURL("/assets/images/movies/avengers.jpg");
        m4.setVisible(true);
        m4.setGroups(Sets.newHashSet(g2, g1));

        Movie m5 = new Movie();
        m5.setAge("PG-12");
        m5.setCountry("USA");
        m5.setDirector("David Leitch");
        m5.setMovieCast("Ryan Reynolds, Josh Brolin, Morena Baccarin");
        m5.setPolishReleaseDate(new Date());
        m5.setWorldReleaseDate(new Date());
        m5.setRunTime(124);
        m5.setStoryline("After successfully working as the mercenary Deadpool for two years, Wade Wilson fails to kill one of his targets on his anniversary with his girlfriend Vanessa. ");
        m5.setTitle("Deadpool 2");
        m5.setImgURL("/assets/images/movies/deadpool.jpg");
        m5.setVisible(true);
        m5.setGroups(Sets.newHashSet(g1, g2));

        Movie m6 = new Movie();
        m6.setAge("PG-12");
        m6.setCountry("USA");
        m6.setDirector("Francis Ford Coppola");
        m6.setMovieCast("Marlon Brando, Al Pacino");
        m6.setPolishReleaseDate(new Date());
        m6.setWorldReleaseDate(new Date());
        m6.setRunTime(177);
        m6.setStoryline("In 1945, at his daughter Connie's wedding, Vito Corleone hears requests in his role as the Godfather, the Don of a New York crime family. Vito's youngest son ...");
        m6.setTitle("The Godfather");
        m6.setImgURL("/assets/images/movies/godfather.jpg");
        m6.setVisible(true);
        m6.setGroups(Sets.newHashSet(g2));

        Movie m7 = new Movie();
        m7.setAge("PG-12");
        m7.setCountry("USA");
        m7.setDirector("Steven Spielberg");
        m7.setMovieCast("Sam Neill, Laura Dern, Jeff Goldblum, Richard Attenborough");
        m7.setPolishReleaseDate(new Date());
        m7.setWorldReleaseDate(new Date());
        m7.setRunTime(127);
        m7.setStoryline("Industrialist John Hammond and his bioengineering company, InGen, have created a theme park called Jurassic Park on Isla Nublar, a Costa Rican island, populated with cloned dinosaurs.");
        m7.setTitle("Jurassic Park");
        m7.setImgURL("/assets/images/movies/jurassic_park.jpg");
        m7.setVisible(true);
        m7.setGroups(Sets.newHashSet(g2));

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
        
        Cinema c4 = new Cinema();
        c4.setName("CinemaPro Łęczna");
        c4.setStreet("Orląt Lwowskich");
        c4.setStreetNumber("5");
        c4.setPostCode("55-222");
        c4.setCity("Łęczna");
        c4.setDescription("Najlepsze kino w Łęcznej");
        c4.setTelephone("723723723");
        c4.setEmail("leczna@cinemapro.com");
        c4.setImgUrl("https://tul.imgix.net/content/article/rivoli-melbourne-cinema.jpg?auto=format,compress&w=740&h=525&fit=crop&crop=edges");
        c4.setVisible(true);
        
        Cinema c5 = new Cinema();
        c5.setName("CinemaPro Psie Pole");
        c5.setStreet("Bierutowska");
        c5.setStreetNumber("35");
        c5.setPostCode("51-322");
        c5.setCity("Wroclaw");
        c5.setDescription("Test description");
        c5.setTelephone("788788731");
        c5.setEmail("psiepole@cinemapro.com");
        c5.setImgUrl("https://tul.imgix.net/content/article/sun-theatre-melbourne.jpg");
        c5.setVisible(true);

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
        h3.setHallNumber(3);
        HashSet<Seat> seats3 = new HashSet<>(seatService.createSeats(15, 20));
        h3.setSeats(seats3);
        h3.setCinema(c1);
        h3.setVisible(true);

        Hall h4 = new Hall();
        h4.setHallNumber(4);
        HashSet<Seat> seats4 = new HashSet<>(seatService.createSeats(14, 13));
        h4.setSeats(seats4);
        h4.setCinema(c1);
        h4.setVisible(true);

        Hall h5 = new Hall();
        h5.setHallNumber(11);
        HashSet<Seat> seats5 = new HashSet<>(seatService.createSeats(14, 13));
        h5.setSeats(seats5);
        h5.setCinema(c2);
        h5.setVisible(true);

        Hall h6 = new Hall();
        h6.setHallNumber(12);
        HashSet<Seat> seats6 = new HashSet<>(seatService.createSeats(14, 13));
        h6.setSeats(seats6);
        h6.setCinema(c2);
        h6.setVisible(true);

        Hall h7 = new Hall();
        h7.setHallNumber(1);
        HashSet<Seat> seats7 = new HashSet<>(seatService.createSeats(14, 13));
        h7.setSeats(seats7);
        h7.setCinema(c3);
        h7.setVisible(true);

        Hall h8 = new Hall();
        h8.setHallNumber(2);
        HashSet<Seat> seats8 = new HashSet<>(seatService.createSeats(14, 13));
        h8.setSeats(seats8);
        h8.setCinema(c3);
        h8.setVisible(true);

        Date todayDate = new Date();
        Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.setTime(todayDate);
        int currentYear = todayCalendar.get(Calendar.YEAR);
        int currentMonth = todayCalendar.get(Calendar.MONTH);
        int currentDay = todayCalendar.get(Calendar.DAY_OF_MONTH);

        Calendar cal = Calendar.getInstance();

        Showing s1 = new Showing();
        cal.set(currentYear, currentMonth, currentDay, 9, 00, 00);
        s1.setScreeningStart(cal.getTime());
        s1.setHall(h1);
        s1.setMovie(m1);

        Showing s2 = new Showing();
        cal.set(currentYear, currentMonth, currentDay, 12, 00, 00);
        s2.setScreeningStart(cal.getTime());
        s2.setHall(h1);
        s2.setMovie(m1);

        Showing s3 = new Showing();
        cal.set(currentYear, currentMonth, currentDay, 15, 00, 00);
        s3.setScreeningStart(cal.getTime());
        s3.setHall(h1);
        s3.setMovie(m1);

        Showing s4 = new Showing();
        cal.set(currentYear, currentMonth, currentDay, 18, 15, 00);
        s4.setScreeningStart(cal.getTime());
        s4.setHall(h1);
        s4.setMovie(m1);

        Showing s5 = new Showing();
        cal.set(currentYear, currentMonth, currentDay + 1, 9, 00, 00);
        s5.setScreeningStart(cal.getTime());
        s5.setHall(h1);
        s5.setMovie(m1);

        Showing s6 = new Showing();
        cal.set(currentYear, currentMonth, currentDay + 1, 12, 00, 00);
        s6.setScreeningStart(cal.getTime());
        s6.setHall(h1);
        s6.setMovie(m1);

        Showing s7 = new Showing();
        cal.set(currentYear, currentMonth, currentDay + 1, 15, 00, 00);
        s7.setScreeningStart(cal.getTime());
        s7.setHall(h1);
        s7.setMovie(m1);

        Showing s8 = new Showing();
        cal.set(currentYear, currentMonth, currentDay + 1, 18, 15, 00);
        s8.setScreeningStart(cal.getTime());
        s8.setHall(h1);
        s8.setMovie(m1);

        Showing s9 = new Showing();
        cal.set(currentYear, currentMonth, currentDay, 9, 00, 00);
        s9.setScreeningStart(cal.getTime());
        s9.setHall(h2);
        s9.setMovie(m2);

        Showing s10 = new Showing();
        cal.set(currentYear, currentMonth, currentDay, 12, 00, 00);
        s10.setScreeningStart(cal.getTime());
        s10.setHall(h2);
        s10.setMovie(m2);

        Showing s11 = new Showing();
        cal.set(currentYear, currentMonth, currentDay, 15, 00, 00);
        s11.setScreeningStart(cal.getTime());
        s11.setHall(h2);
        s11.setMovie(m2);

        Showing s12 = new Showing();
        cal.set(currentYear, currentMonth, currentDay, 18, 15, 00);
        s12.setScreeningStart(cal.getTime());
        s12.setHall(h2);
        s12.setMovie(m2);

        Showing s13 = new Showing();
        cal.set(currentYear, currentMonth, currentDay + 1, 9, 00, 00);
        s13.setScreeningStart(cal.getTime());
        s13.setHall(h2);
        s13.setMovie(m2);

        Showing s14 = new Showing();
        cal.set(currentYear, currentMonth, currentDay + 1, 12, 00, 00);
        s14.setScreeningStart(cal.getTime());
        s14.setHall(h2);
        s14.setMovie(m2);

        Showing s15 = new Showing();
        cal.set(currentYear, currentMonth, currentDay + 1, 15, 00, 00);
        s15.setScreeningStart(cal.getTime());
        s15.setHall(h2);
        s15.setMovie(m2);

        Showing s16 = new Showing();
        cal.set(currentYear, currentMonth, currentDay + 1, 18, 15, 00);
        s16.setScreeningStart(cal.getTime());
        s16.setHall(h2);
        s16.setMovie(m2);

        Showing s17 = new Showing();
        cal.set(currentYear, currentMonth, currentDay, 9, 00, 00);
        s17.setScreeningStart(cal.getTime());
        s17.setHall(h3);
        s17.setMovie(m3);

        Showing s18 = new Showing();
        cal.set(currentYear, currentMonth, currentDay, 12, 00, 00);
        s18.setScreeningStart(cal.getTime());
        s18.setHall(h3);
        s18.setMovie(m3);

        Showing s19 = new Showing();
        cal.set(currentYear, currentMonth, currentDay, 15, 00, 00);
        s19.setScreeningStart(cal.getTime());
        s19.setHall(h3);
        s19.setMovie(m3);

        Showing s20 = new Showing();
        cal.set(currentYear, currentMonth, currentDay, 18, 15, 00);
        s20.setScreeningStart(cal.getTime());
        s20.setHall(h3);
        s20.setMovie(m3);

        Showing s21 = new Showing();
        cal.set(currentYear, currentMonth, currentDay + 1, 9, 00, 00);
        s21.setScreeningStart(cal.getTime());
        s21.setHall(h3);
        s21.setMovie(m3);

        Showing s22 = new Showing();
        cal.set(currentYear, currentMonth, currentDay + 1, 12, 00, 00);
        s22.setScreeningStart(cal.getTime());
        s22.setHall(h3);
        s22.setMovie(m3);

        Showing s23 = new Showing();
        cal.set(currentYear, currentMonth, currentDay + 1, 15, 00, 00);
        s23.setScreeningStart(cal.getTime());
        s23.setHall(h3);
        s23.setMovie(m3);

        Showing s24 = new Showing();
        cal.set(currentYear, currentMonth, currentDay + 1, 18, 15, 00);
        s24.setScreeningStart(cal.getTime());
        s24.setHall(h3);
        s24.setMovie(m3);

        Showing s25 = new Showing();
        cal.set(currentYear, currentMonth, currentDay, 21, 00, 00);
        s25.setScreeningStart(cal.getTime());
        s25.setHall(h1);
        s25.setMovie(m4);

        Showing s26 = new Showing();
        cal.set(currentYear, currentMonth, currentDay, 22, 00, 00);
        s26.setScreeningStart(cal.getTime());
        s26.setHall(h2);
        s26.setMovie(m4);

        Showing s27 = new Showing();
        cal.set(currentYear, currentMonth, currentDay, 23, 15, 00);
        s27.setScreeningStart(cal.getTime());
        s27.setHall(h3);
        s27.setMovie(m4);

        Showing s28 = new Showing();
        cal.set(currentYear, currentMonth, currentDay, 9, 00, 00);
        s28.setScreeningStart(cal.getTime());
        s28.setHall(h4);
        s28.setMovie(m5);

        Showing s29 = new Showing();
        cal.set(currentYear, currentMonth, currentDay + 1, 9, 00, 00);
        s29.setScreeningStart(cal.getTime());
        s29.setHall(h4);
        s29.setMovie(m5);

        Showing s30 = new Showing();
        cal.set(currentYear, currentMonth, currentDay + 1, 12, 00, 00);
        s30.setScreeningStart(cal.getTime());
        s30.setHall(h4);
        s30.setMovie(m5);

        Showing s31 = new Showing();
        cal.set(currentYear, currentMonth, currentDay + 1, 15, 00, 00);
        s31.setScreeningStart(cal.getTime());
        s31.setHall(h4);
        s31.setMovie(m5);

        Showing s32 = new Showing();
        cal.set(currentYear, currentMonth, currentDay + 1, 18, 15, 00);
        s32.setScreeningStart(cal.getTime());
        s32.setHall(h4);
        s32.setMovie(m5);

        
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
        e1.setEmail("employee@example.com");
        e1.setPassword(bCryptPasswordEncoder.encode("haslo123"));

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
        e2.setPassword(bCryptPasswordEncoder.encode("haslo123"));
        
        Employee e3 = new Employee();
        cal.set(2001, 1, 1, 13, 15, 00);
        e3.setStartingDateOfEmployment(cal.getTime());
        e3.setName("Józef");
        e3.setSurname("Małec");
        e3.setEmail("j.malec@email.com");
        e3.setCity("Wroclaw");
        e3.setStreet("Prosta");
        e3.setPostCode("55-535");
        e3.setStreetNumber("17/8");
        e3.setTelephone("777888777");
        e3.setCinema(c1);
        e3.setPassword(bCryptPasswordEncoder.encode("haslo123"));
        
        Employee e4 = new Employee();
        cal.set(2002, 1, 1, 13, 15, 00);
        e4.setStartingDateOfEmployment(cal.getTime());
        e4.setName("Karol");
        e4.setSurname("Wikary");
        e4.setEmail("k.wikary@email.com");
        e4.setCity("Wroclaw");
        e4.setStreet("Prosta");
        e4.setPostCode("55-535");
        e4.setStreetNumber("17/8");
        e4.setTelephone("777888777");
        e4.setCinema(c2);
        e4.setPassword(bCryptPasswordEncoder.encode("haslo123"));
        
        Employee e5 = new Employee();
        cal.set(2003, 1, 1, 13, 15, 00);
        e5.setStartingDateOfEmployment(cal.getTime());
        e5.setName("Karolina");
        e5.setSurname("Wiśnia");
        e5.setEmail("k.wisnia@email.com");
        e5.setCity("Wroclaw");
        e5.setStreet("Prosta");
        e5.setPostCode("55-535");
        e5.setStreetNumber("17/8");
        e5.setTelephone("777888777");
        e5.setCinema(c2);
        e5.setPassword(bCryptPasswordEncoder.encode("haslo123"));
        
        Employee e6 = new Employee();
        cal.set(1999, 1, 1, 13, 15, 00);
        e6.setStartingDateOfEmployment(cal.getTime());
        e6.setName("Róża");
        e6.setSurname("Kowalska");
        e6.setEmail("r.kowalska@email.com");
        e6.setCity("Wroclaw");
        e6.setStreet("Prosta");
        e6.setPostCode("55-535");
        e6.setStreetNumber("17/8");
        e6.setTelephone("777888777");
        e6.setCinema(c2);
        e6.setPassword(bCryptPasswordEncoder.encode("haslo123"));
        
        Employee e7 = new Employee();
        cal.set(1999, 1, 1, 13, 15, 00);
        e7.setStartingDateOfEmployment(cal.getTime());
        e7.setName("Oliwia");
        e7.setSurname("Opalska");
        e7.setEmail("o.opalska@email.com");
        e7.setCity("Wroclaw");
        e7.setStreet("Prosta");
        e7.setPostCode("55-535");
        e7.setStreetNumber("17/8");
        e7.setTelephone("777888777");
        e7.setCinema(c2);
        e7.setPassword(bCryptPasswordEncoder.encode("haslo123"));
        
        Client cl1 = new Client();
        cl1.setEmail("user1@email.com");
        cl1.setBirthDate(new Date());
        cl1.setEmail("client@example.com");
        cl1.setPassword(bCryptPasswordEncoder.encode("haslo123"));

        Client cl2 = new Client();
        cl2.setEmail("client2@example.com");
        cl2.setBirthDate(new Date());
        cl2.setPassword(bCryptPasswordEncoder.encode("haslo123"));


        Client cl3 = new Client();
        cl3.setEmail("client3@example.com");
        cl3.setBirthDate(new Date());
        cl3.setPassword(bCryptPasswordEncoder.encode("haslo123"));

        Client cl4 = new Client();
        cl4.setEmail("client4@example.com");
        cl4.setBirthDate(new Date());
        cl4.setPassword(bCryptPasswordEncoder.encode("haslo123"));

        Admin a1 = new Admin();
        a1.setEmail("admin@example.com");
        a1.setPassword(bCryptPasswordEncoder.encode("haslo123"));

        Reservation r1 = new Reservation();
        r1.setShowing(s1);
        Iterator<Seat> seatsIt = s1.getHall().getSeats().iterator();
        Ticket t1 = new Ticket();
        Seat seat = seatsIt.next();
        t1.setSeat(seat);
        t1.setTicketType(tt1);

        Ticket t2 = new Ticket();
        seat = seatsIt.next();
        t2.setSeat(seat);
        t2.setTicketType(tt2);

        Set<Ticket> tickets = new HashSet<>(Arrays.asList(t1, t2));
        r1.setTickets(tickets);
        r1.setClient(cl1);

        Reservation r2 = new Reservation();
        r2.setShowing(s1);

        Ticket t3 = new Ticket();
        seat = seatsIt.next();
        t3.setSeat(seat);
        t3.setTicketType(tt3);

        tickets = new HashSet<>(Arrays.asList(t3));
        r2.setTickets(tickets);
        r2.setClient(cl2);

        Reservation r3 = new Reservation();
        r3.setShowing(s2);
        seatsIt = s2.getHall().getSeats().iterator();

        Ticket t4 = new Ticket();
        seat = seatsIt.next();
        t4.setSeat(seat);
        t4.setTicketType(tt1);

        Ticket t5 = new Ticket();
        seat = seatsIt.next();
        t5.setSeat(seat);
        t5.setTicketType(tt2);

        Ticket t6 = new Ticket();
        seat = seatsIt.next();
        t6.setSeat(seat);
        t6.setTicketType(tt3);

        Set<Ticket> tickets2 = new HashSet<>(Arrays.asList(t4, t5, t6));
        r3.setTickets(tickets2);
        r3.setClient(cl3);

        groupRepository.saveAll(Lists.newArrayList(g1, g2));
        clientRepository.saveAll(Lists.newArrayList(cl1, cl2, cl3, cl4));
		movieRepository.saveAll(Lists.newArrayList(m1, m2, m3, m4, m5, m6, m7));
        cinemaRepository.saveAll(Lists.newArrayList(c1, c2, c3, c4, c5));
        hallRepository.saveAll(Lists.newArrayList(h1, h2, h3, h4, h5));
        showingRepository.saveAll(Lists.newArrayList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16,
                s17, s18, s19, s20, s21, s22, s23, s24, s25, s26, s27, s28, s29, s30, s31, s32));
        ticketTypeRepository.saveAll(Lists.newArrayList(tt1, tt2, tt3));
        ticketRepository.saveAll(Lists.newArrayList(t1, t2, t3, t4, t5, t6));
        employeeRepository.saveAll(Lists.newArrayList(e1, e2, e3, e4, e5, e6, e7));
        reservationRepository.saveAll(Lists.newArrayList(r1, r2, r3));
        adminRepository.saveAll(Lists.newArrayList(a1));
    }

}