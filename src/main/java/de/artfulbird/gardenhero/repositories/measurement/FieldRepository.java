package de.artfulbird.gardenhero.repositories.measurement;

import de.artfulbird.gardenhero.models.measurement.Field;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FieldRepository extends CrudRepository<Field, Long> {
    Optional<Field> findById(Long id);
}
