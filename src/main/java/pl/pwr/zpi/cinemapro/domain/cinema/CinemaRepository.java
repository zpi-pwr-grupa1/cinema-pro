package pl.pwr.zpi.cinemapro.domain.cinema;

import pl.pwr.zpi.cinemapro.domain.cinema.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    List<Cinema> findByVisible(boolean visible);
}
