package pl.pwr.zpi.cinemapro.domain.showing;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.util.Date;

// TODO configure it to be initialized only when specific profile is used

@Component
public class ShowingInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ShowingRepository showingRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    private void init(){
        Showing s1 = new Showing();

        showingRepository.saveAll(Lists.newArrayList(s1));
    }

}