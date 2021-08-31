package de.artfulbird.gardenhero.repositories.measurement;

import de.artfulbird.gardenhero.models.measurement.Sector;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SectorRepository extends CrudRepository<Sector, Long> {
    Optional<Sector> findById(Long id);
}
