package pl.pwr.zpi.cinemapro.client;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

// TODO configure it to be initialized only when specific profile is used

@Component
public class ClientInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    private void init(){
        Client c1 = new Client();
        c1.setUsername("user1");
        c1.setPassword("password1");

        Client c2 = new Client();
        c2.setUsername("user2");
        c2.setPassword("password2");

        Client c3 = new Client();
        c3.setUsername("user3");
        c3.setPassword("password3");

        clientRepository.saveAll(Lists.newArrayList(c1, c2, c3));
    }

}