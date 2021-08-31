package de.artfulbird.gardenhero.repositories.operation;

import de.artfulbird.gardenhero.models.operation.PumpData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PumpDataRepository extends CrudRepository<PumpData, Long> {
    Optional<PumpData> findById(Long id);
}
