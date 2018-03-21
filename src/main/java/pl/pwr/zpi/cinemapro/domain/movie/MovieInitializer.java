package pl.pwr.zpi.cinemapro.domain.movie;

import com.google.common.collect.Lists;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

// TODO configure it to be initialized only when specific profile is used

@Component
public class MovieInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    private void init(){
        
        Movie c1 = new Movie();
        c1.setAge("PG-13");
        c1.setCountry("USA");
        c1.setDirector("Michael Bay");
        c1.setMovieCast("Shia LaBeouf, Megan Fox, Josh Duhamel");
        c1.setPolishReleaseDate(new Date(System.currentTimeMillis()));
        c1.setWorldReleaseDate(new Date(System.currentTimeMillis()));
        c1.setRunTime(144);
        c1.setStoryline("Something with robots and EXPLOSIONSSSS!!!");
        c1.setTitle("Transformers");
        c1.setVisible(true);

        movieRepository.saveAll(Lists.newArrayList(c1));
    }

}