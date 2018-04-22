package pl.pwr.zpi.cinemapro.domain.showing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ShowingService {

    @Autowired
    ShowingRepository showingRepository;

    public List<Showing> findAll() {
        return showingRepository.findAll();
    }

    public List<Showing> findAfter(Date date) {
        return showingRepository.findByScreeningStartAfter(date);
    }

    public Showing findById(UUID id) {
        return showingRepository.findById(id);
    }

    public Showing save(Showing showing) {
        return showingRepository.save(showing);
    }

    public void delete(Showing showing) {
        showingRepository.delete(showing);
    }

    public boolean existsOverlappingShowing(Showing showing){
        Date currentDate = new Date();
        List<Showing> showings = findAfter(currentDate);
        showings.stream().filter(s-> s.getHall().getId().equals(showing.getHall().getId()));
        for(Showing s : showings){
            if(checkIfOverlap(s, showing)){
                return true;
            }
        }
        return false;
    }

    public boolean checkIfOverlap(Showing s1, Showing s2) {
        Date startDate1 = s1.getScreeningStart();
        Date endDate1 = getScreeningEnd(s1);
        Date startDate2 = s2.getScreeningStart();
        Date endDate2 = getScreeningEnd(s2);
        boolean result = startDate1.before(endDate2) && startDate2.before(endDate1);
        return result;
    }

    public Date getScreeningEnd(Showing showing) {
        final long ONE_MINUTE_IN_MILIS = 60000;

        int movieLength = showing.getMovie().getRunTime();
        long screeningStart = showing.getScreeningStart().getTime();
        Date screeningEnd = new Date(screeningStart + (movieLength * ONE_MINUTE_IN_MILIS));
        return  screeningEnd;
    }
}
