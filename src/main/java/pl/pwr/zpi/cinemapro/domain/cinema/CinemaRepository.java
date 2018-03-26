package pl.pwr.zpi.cinemapro.domain.cinema;

import pl.pwr.zpi.cinemapro.domain.cinema.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    List<Cinema> findByVisible(boolean visible);

    Cinema findById(UUID uuid);

    Cinema findByName(String name);

}
