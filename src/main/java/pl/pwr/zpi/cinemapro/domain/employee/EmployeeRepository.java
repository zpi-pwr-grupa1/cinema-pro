package pl.pwr.zpi.cinemapro.domain.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByVisible(boolean visible);

    Employee findById(UUID uuid);

    List<Employee> findByCinemaId(UUID uuid);

    List<Employee> findByEmployedAndCinemaId(boolean employed, UUID uuid);
}
