package pl.pwr.zpi.cinemapro.domain.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findById(UUID id);

    List<Reservation> findByShowingId(UUID id);

    List<Reservation> findByClientId(UUID id);
}
