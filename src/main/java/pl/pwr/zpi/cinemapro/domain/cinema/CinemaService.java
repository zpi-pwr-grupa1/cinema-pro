package pl.pwr.zpi.cinemapro.domain.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.zpi.cinemapro.domain.hall.Hall;
import pl.pwr.zpi.cinemapro.domain.hall.HallRepository;
import pl.pwr.zpi.cinemapro.domain.showing.Showing;
import pl.pwr.zpi.cinemapro.domain.showing.ShowingRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CinemaService {

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    ShowingRepository showingRepository;

    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    public List<Cinema> findAllVisible() {
        return cinemaRepository.findByVisible(true);
    }

    public Cinema findById(UUID id){
        return cinemaRepository.findById(id);
    }

    public Cinema findByName(String name) {
        return cinemaRepository.findByName(name);
    }

    public Cinema save(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    public void setNotVisible(Cinema cinema) {
        cinema.setVisible(false);
        cinemaRepository.save(cinema);
    }

    public List<Hall> findHallsById(UUID cinemaId){
        return hallRepository.findByCinemaId(cinemaId);
    }

    public List<Hall> findVisibleHallsById(UUID cinemaId){
        return hallRepository.findByVisibleAndCinemaId(true, cinemaId);
    }

    public List<Showing> findShowingsById(UUID cinemaId){
        List<Hall> hallsInCinema = findVisibleHallsById(cinemaId);
        List<Showing> showingsInCinema = new ArrayList<>();
        for (Hall h : hallsInCinema) {
            List<Showing> showingsInHall = showingRepository.findByHallId(h.getId());
            showingsInCinema.addAll(showingsInHall);
        }
        return showingsInCinema;
    }

    public List<Showing> findPlannedShowingsById(UUID cinemaId){
        List<Hall> hallsInCinema = findVisibleHallsById(cinemaId);
        List<Showing> showingsInCinema = new ArrayList<>();
        for (Hall h : hallsInCinema) {
            List<Showing> showingsInHall = showingRepository.findByScreeningStartAfterAndHallId(new Date(), h.getId());
            showingsInCinema.addAll(showingsInHall);
        }
        return showingsInCinema;
    }

    public List<Showing> findShowingsOnDate(UUID cinemaId, Date date) {
        List<Showing> showingsInCinema = findShowingsById(cinemaId);
        Date dateWithoutTime = getDateWithoutTime(date);
        List<Showing> showingsOnDate =
                showingsInCinema.stream()
                .filter(showing -> getDateWithoutTime(showing.getScreeningStart()).equals(dateWithoutTime))
                .sorted(Comparator.comparing(Showing::getScreeningStart))
                .collect(Collectors.toList());
        return showingsOnDate;
    }

    private Date getDateWithoutTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateWithoutTime = date;
        try {
            dateWithoutTime = sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateWithoutTime;


    }

}
