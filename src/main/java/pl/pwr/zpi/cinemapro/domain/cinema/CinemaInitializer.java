package pl.pwr.zpi.cinemapro.domain.cinema;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

// TODO configure it to be initialized only when specific profile is used

@Component
public class CinemaInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //init();
    }

    private void init(){
        Cinema c1 = new Cinema();
        c1.setAddress("Wroclaw, Grabiszynska");
        c1.setDescription("Jakis opis");
        c1.setTelephone("710000000");
        c1.setVisible(true);

        cinemaRepository.saveAll(Lists.newArrayList(c1));
    }

}