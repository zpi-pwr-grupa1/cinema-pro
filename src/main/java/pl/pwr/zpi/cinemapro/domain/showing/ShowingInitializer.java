package pl.pwr.zpi.cinemapro.domain.showing;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.pwr.zpi.cinemapro.domain.hall.Hall;
import pl.pwr.zpi.cinemapro.domain.hall.HallRepository;
import pl.pwr.zpi.cinemapro.domain.movie.Movie;
import pl.pwr.zpi.cinemapro.domain.movie.MovieRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

// TODO configure it to be initialized only when specific profile is used

@Component
public class ShowingInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ShowingRepository showingRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    private void init(){
        List<Hall> halls = hallRepository.findAll();
        List<Movie> movies = movieRepository.findAll();
        Showing s1 = new Showing();
        s1.setScreeningStart(new Date());
        s1.setHall(halls.get(0));
        s1.setMovie(movies.get(0));

        Showing s2 = new Showing();
        Calendar cal = Calendar.getInstance();
        cal.set(2053, 1, 1, 15, 15, 00);

        s2.setScreeningStart(cal.getTime());
        s2.setHall(halls.get(0));
        s2.setMovie(movies.get(0));

        showingRepository.saveAll(Lists.newArrayList(s1, s2));
    }

}