package pl.pwr.zpi.cinemapro.common.initialization;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

// TODO configure it to be initialized only when specific profile is used

@Component
public class ClientInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    private void init(){
    }

}