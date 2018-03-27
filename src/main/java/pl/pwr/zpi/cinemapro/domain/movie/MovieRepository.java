package pl.pwr.zpi.cinemapro.domain.movie;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByVisible(boolean visible);
}
