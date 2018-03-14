package pl.pwr.zpi.cinemapro.domain.client;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.util.Date;

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
        c1.setEmail("user1@email.com");
        c1.setPassword("password1");
        c1.setBirthDate(new Date());

        Client c2 = new Client();
        c2.setEmail("user2@email.com");
        c2.setPassword("password2");
        c2.setBirthDate(new Date());

        Client c3 = new Client();
        c3.setEmail("user3@email.com");
        c3.setPassword("password3");
        c3.setBirthDate(new Date());

        clientRepository.saveAll(Lists.newArrayList(c1, c2, c3));
    }

}