package de.artfulbird.gardenhero.repositories.measurement;

import de.artfulbird.gardenhero.models.measurement.Moisture;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MoistureRepository extends CrudRepository<Moisture, Long> {
    Optional<Moisture> findById(Long id);
}
