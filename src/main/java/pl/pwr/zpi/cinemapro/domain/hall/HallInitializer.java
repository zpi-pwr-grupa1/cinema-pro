package pl.pwr.zpi.cinemapro.domain.hall;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

// TODO configure it to be initialized only when specific profile is used

@Component
public class HallInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private HallRepository HallRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    private void init() {
        /*
        Hall s1 = new Hall();
        s1.setHallRow(0);
        s1.setHallColumn(0);

        HallRepository.saveAll(Lists.newArrayList(s1));
        */
    }

}