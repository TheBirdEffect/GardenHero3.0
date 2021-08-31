package de.artfulbird.gardenhero.repositories.operation;

import de.artfulbird.gardenhero.models.operation.Program;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProgramRepository extends CrudRepository<Program, Long> {
    Optional<Program> findById(Long id);

    Optional<Program> findByNameIgnoreCase(String name);

    /*
    Search queries for Threshold Moisture High
    */
    Optional<Program> findProgramByMoistureThresholdHighIsGreaterThanEqual(float moisture);     // Sort greater than equal

    Optional<Program> findProgramByMoistureThresholdHighIsLessThanEqual(float moisture);        // Sort less than equal

    Optional<Program> findProgramByMoistureThresholdHighIsBetween(float moistureOne, float moistureTwo); // Sort between

    /*
    Search queries for Threshold Moisture High
    */
    Optional<Program> findProgramByMoistureThresholdLowIsGreaterThanEqual(float moisture);     // Sort greater than equal

    Optional<Program> findProgramByMoistureThresholdLowIsLessThanEqual(float moisture);        // Sort less than equal

    Optional<Program> findProgramByMoistureThresholdLowIsBetween(float moistureOne, float moistureTwo); // Sort between
}
