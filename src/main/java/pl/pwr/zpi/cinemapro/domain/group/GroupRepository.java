package pl.pwr.zpi.cinemapro.domain.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByVisible(boolean visible);

    Group findById(UUID uuid);

    List<Group> findByMovieId(UUID uuid);

    List<Group> findByClientId(UUID uuid);
}
