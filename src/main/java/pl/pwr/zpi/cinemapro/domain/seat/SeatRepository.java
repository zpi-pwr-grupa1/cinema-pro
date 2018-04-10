package pl.pwr.zpi.cinemapro.domain.seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByVisible(boolean visible);

    Seat findById(UUID uuid);
}
