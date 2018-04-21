package pl.pwr.zpi.cinemapro.domain.cinema;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.pwr.zpi.cinemapro.domain.hall.Hall;
import pl.pwr.zpi.cinemapro.domain.hall.HallRepository;
import pl.pwr.zpi.cinemapro.domain.movie.Movie;
import pl.pwr.zpi.cinemapro.domain.movie.MovieRepository;
import pl.pwr.zpi.cinemapro.domain.seat.Seat;
import pl.pwr.zpi.cinemapro.domain.seat.SeatService;
import pl.pwr.zpi.cinemapro.domain.showing.Showing;
import pl.pwr.zpi.cinemapro.domain.showing.ShowingRepository;

import java.util.*;

// TODO configure it to be initialized only when specific profile is used

@Component
public class CinemaInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowingRepository showingRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private SeatService seatService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    private void init() {
        Movie m1 = new Movie();
        m1.setAge("PG-13");
        m1.setCountry("USA");
        m1.setDirector("Michael Bay");
        m1.setMovieCast("Shia LaBeouf, Megan Fox, Josh Duhamel");
        m1.setPolishReleaseDate(new Date(System.currentTimeMillis()));
        m1.setWorldReleaseDate(new Date(System.currentTimeMillis()));
        m1.setRunTime(144);
        m1.setStoryline("Something with robots and EXPLOSIONSSSS!!!");
        m1.setTitle("Transformers");
        m1.setVisible(true);

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

        Hall h1 = new Hall();
        h1.setHallNumber(0);
        HashSet<Seat> seats = new HashSet<>(seatService.createSeats(10, 10));
        h1.setSeats(seats);
        h1.setCinema(c1);
        h1.setVisible(true);

        Hall h2 = new Hall();
        h2.setHallNumber(1);
        HashSet<Seat> seats2 = new HashSet<>(seatService.createSeats(20, 15));
        h2.setSeats(seats2);
        h2.setCinema(c1);
        h2.setVisible(true);

        //TODO here initialise showings for cinemas

        Showing s1 = new Showing();
        s1.setScreeningStart(new Date());
        s1.setHall(h1);
        s1.setMovie(m1);

        Showing s2 = new Showing();
        Calendar cal = Calendar.getInstance();
        cal.set(2053, 1, 1, 15, 15, 00);
        s2.setScreeningStart(cal.getTime());
        s2.setHall(h2);
        s2.setMovie(m1);

        Cinema c2 = new Cinema();
        c2.setName("CinemaPro Legnicka");
        c2.setStreet("Legnicka");
        c2.setStreetNumber("15");
        c2.setPostCode("11-222");
        c2.setCity("Wroclaw");
        c2.setDescription("Test description");
        c2.setTelephone("71111222");
        c2.setEmail("email2@email.com");
        c2.setImgUrl("https://i.ytimg.com/vi/PjHumS-GVAo/hqdefault.jpg");
        c2.setVisible(false);

        movieRepository.saveAll(Lists.newArrayList(m1));
        cinemaRepository.saveAll(Lists.newArrayList(c1, c2));
        hallRepository.saveAll(Lists.newArrayList(h1, h2));
        showingRepository.saveAll(Lists.newArrayList(s1, s2));
    }

}