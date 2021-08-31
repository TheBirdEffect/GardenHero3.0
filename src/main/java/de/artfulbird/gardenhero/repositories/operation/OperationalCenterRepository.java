package de.artfulbird.gardenhero.repositories.operation;

import de.artfulbird.gardenhero.models.operation.OperationalCenter;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OperationalCenterRepository extends CrudRepository<OperationalCenter, Long> {
    Optional<OperationalCenter> findById(Long id);
    Optional<OperationalCenter> findByNameIgnoreCase(String name);
}
