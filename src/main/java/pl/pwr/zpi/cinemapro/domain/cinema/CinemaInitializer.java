package pl.pwr.zpi.cinemapro.domain.cinema;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.pwr.zpi.cinemapro.domain.hall.Hall;
import pl.pwr.zpi.cinemapro.domain.seat.Seat;
import pl.pwr.zpi.cinemapro.domain.seat.SeatService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// TODO configure it to be initialized only when specific profile is used

@Component
public class CinemaInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private SeatService seatService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    private void init() {
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

        Hall h2 = new Hall();
        h2.setHallNumber(1);
        HashSet<Seat> seats2 = new HashSet<>(seatService.createSeats(20, 15));
        h2.setSeats(seats2);
        c1.setHalls(Sets.newHashSet(h1, h2));

        //TODO here initialise showings for cinemas


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


        cinemaRepository.saveAll(Lists.newArrayList(c1, c2));
    }

}