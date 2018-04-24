package pl.pwr.zpi.cinemapro.common.initialization;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.pwr.zpi.cinemapro.domain.user.User;
import pl.pwr.zpi.cinemapro.domain.user.UserRepository;

@Component
public class UserInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    private void init() {
        
        User u = new User();
        u.setUsername("user@example.com");
        u.setPassword(bCryptPasswordEncoder.encode("haslo123"));

        userRepository.saveAll(Lists.newArrayList(u));
    }

}