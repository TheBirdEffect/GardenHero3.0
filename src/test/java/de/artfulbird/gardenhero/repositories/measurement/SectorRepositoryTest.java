package de.artfulbird.gardenhero.repositories.measurement;

import de.artfulbird.gardenhero.models.measurement.Sector;
import de.artfulbird.gardenhero.models.operation.OperationalCenter;
import de.artfulbird.gardenhero.repositories.operation.OperationalCenterRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active=test")
class SectorRepositoryTest {

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    OperationalCenterRepository operationalCenterRepository;

    @BeforeEach
    void setUp() {
        sectorRepository.deleteAll();
        operationalCenterRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        sectorRepository.deleteAll();
        operationalCenterRepository.deleteAll();
    }

    @Test
    void shouldAddSector() {
        //Given
        OperationalCenter givenCenter = OperationalCenter.builder()
                .name("Base0")
                .ipAddress("192.168.1.17")
                .build();
        operationalCenterRepository.save(givenCenter);

        Sector givenSector = Sector.builder()
                .name("Sector1")
                .ipAddress("192.168.1.18")
                .operationalCenter(givenCenter)
                .build();
        //When
        Sector saved = sectorRepository.save(givenSector);

        //Then
        Assertions.assertThat(saved).isEqualTo(givenSector);
    }

    @Test
    void shouldUpdateSector() {
        //Given
        OperationalCenter givenCenter = OperationalCenter.builder()
                .name("Base0")
                .ipAddress("192.168.1.17")
                .build();
        operationalCenterRepository.save(givenCenter);

        Sector givenSector = Sector.builder()
                .name("Sector1")
                .ipAddress("192.168.1.18")
                .operationalCenter(givenCenter)
                .build();
        Sector saved = sectorRepository.save(givenSector);

        //When
        String nameOfSectorBefore = givenSector.getName();
        saved.setName("Sector0");
        Sector result = sectorRepository.save(saved);

        //Then
        Assertions.assertThat(saved.getName()).isNotEqualTo(nameOfSectorBefore);
    }

    @Test
    void shouldDeleteSector() {
        //Given
        OperationalCenter givenCenter = OperationalCenter.builder()
                .name("Base0")
                .ipAddress("192.168.1.17")
                .build();
        operationalCenterRepository.save(givenCenter);

        Sector givenSector = Sector.builder()
                .name("Sector1")
                .ipAddress("192.168.1.18")
                .operationalCenter(givenCenter)
                .build();
        Sector saved = sectorRepository.save(givenSector);

        //When
        sectorRepository.delete(saved);


        //Then
        Assertions.assertThat(sectorRepository.findById(saved.getId())).isNotPresent();
    }
}