package pl.pwr.zpi.cinemapro.domain.seat;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

// TODO configure it to be initialized only when specific profile is used

@Component
public class SeatInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    private void init() {
        /*
        Seat s1 = new Seat();
        s1.setSeatRow(0);
        s1.setSeatColumn(0);

        seatRepository.saveAll(Lists.newArrayList(s1));
        */
    }

}