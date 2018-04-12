package pl.pwr.zpi.cinemapro.domain.showing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShowingRepository extends JpaRepository<Showing, Long>{
    Showing findById(UUID id);
}
