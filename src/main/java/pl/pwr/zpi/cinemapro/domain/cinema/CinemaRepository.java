package pl.pwr.zpi.cinemapro.domain.cinema;

import pl.pwr.zpi.cinemapro.domain.cinema.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {}
