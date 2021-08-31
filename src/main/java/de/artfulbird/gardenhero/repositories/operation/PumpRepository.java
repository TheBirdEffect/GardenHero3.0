package de.artfulbird.gardenhero.repositories.operation;

import de.artfulbird.gardenhero.models.operation.Pump;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PumpRepository extends CrudRepository<Pump, Long> {
    Optional<Pump> findById(Long id);
}
