package pl.pwr.zpi.cinemapro.domain.hall;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    List<Hall> findByVisible(boolean visible);

    Hall findById(UUID uuid);
}
