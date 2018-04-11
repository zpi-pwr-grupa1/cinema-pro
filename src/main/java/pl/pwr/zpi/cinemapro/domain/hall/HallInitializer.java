package pl.pwr.zpi.cinemapro.domain.hall;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.pwr.zpi.cinemapro.domain.cinema.Cinema;
import pl.pwr.zpi.cinemapro.domain.cinema.CinemaRepository;

import java.util.List;

// TODO configure it to be initialized only when specific profile is used

@Component
public class HallInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private HallRepository HallRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    private void init() {
        Hall h1 = new Hall();
        h1.setHallNumber(1);
        Hall h2 = new Hall();
        h2.setHallNumber(2);
        h2.setVisible(true);
        List<Cinema> cinemas = cinemaRepository.findAll();
        h1.setCinema(cinemas.get(0));
        h2.setCinema(cinemas.get(0));


        HallRepository.saveAll(Lists.newArrayList(h1, h2));

    }

}