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
        init();
    }

    private void init(){
        Cinema c1 = new Cinema();
        c1.setName("CinemaPro Grabiszynska");
        c1.setStreet("Grabiszynska");
        c1.setStreetNumber("10");
        c1.setPostCode("11-111");
        c1.setCity("Wroclaw");
        c1.setDescription("Jakis opis");
        c1.setTelephone("710000000");
        c1.setEmail("email@email.com");
        c1.setImgUrl("https://img.buzzfeed.com/buzzfeed-static/static/2014-10/2/15/enhanced/webdr02/longform-original-30174-1412277841-15.jpg?downsize=715:*&output-format=auto&output-quality=auto");
        c1.setVisible(true);

        Cinema c2 = new Cinema();
        c2.setName("CinemaPro Legnicka");
        c2.setStreet("Legnicka");
        c2.setStreetNumber("15");
        c2.setPostCode("11-222");
        c2.setCity("Wroclaw");
        c2.setDescription("Test description");
        c2.setTelephone("71111222");
        c2.setEmail("email2@email.com");
        c2.setVisible(false);

        cinemaRepository.saveAll(Lists.newArrayList(c1, c2));
    }

}