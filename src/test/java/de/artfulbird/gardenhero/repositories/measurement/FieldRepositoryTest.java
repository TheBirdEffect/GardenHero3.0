package de.artfulbird.gardenhero.repositories.measurement;

import de.artfulbird.gardenhero.models.measurement.Field;
import de.artfulbird.gardenhero.models.measurement.Sector;
import de.artfulbird.gardenhero.models.operation.OperationalCenter;
import de.artfulbird.gardenhero.models.operation.Program;
import de.artfulbird.gardenhero.repositories.operation.OperationalCenterRepository;
import de.artfulbird.gardenhero.repositories.operation.ProgramRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FieldRepositoryTest {

    @Autowired
    OperationalCenterRepository operationalCenterRepository;

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    FieldRepository fieldRepository;

    @Autowired
    ProgramRepository programRepository;

    @BeforeEach
    void setUp() {
        fieldRepository.deleteAll();
        sectorRepository.deleteAll();
        programRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        fieldRepository.deleteAll();
        sectorRepository.deleteAll();
        programRepository.deleteAll();
    }

    @Test
    void shouldAddField() {
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
        sectorRepository.save(givenSector);

        Field givenField = Field.builder()
                .name("Field1")
                .sector(givenSector)
                .build();
        //When
        Field saved = fieldRepository.save(givenField);

        //Then
        Assertions.assertThat(saved).isEqualTo(givenField);
    }

    @Test
    void shouldUpdateFieldName() {
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
        sectorRepository.save(givenSector);

        Field givenField = Field.builder()
                .name("Field1")
                .sector(givenSector)
                .build();
        Field saved = fieldRepository.save(givenField);

        //When
        String fieldNameBefore = saved.getName();
        saved.setName("Field0");
        Field result = fieldRepository.save(saved);

        //Then
        Assertions.assertThat(saved.getName()).isNotEqualTo(fieldNameBefore);
    }

    @Test
    void shouldDeleteField() {
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
        sectorRepository.save(givenSector);

        Program givenProgram = Program.builder()
                .name("Dry")
                .type("Type0")
                .moistureThresholdLow(50)
                .moistureThresholdHigh(70)
                .wateringDurationPerIntervalInSeconds(3)
                .wateringBreakPerIntervalInSeconds(10)
                .wateringFrequencyPerIntervalInSeconds(5)
                .isEnabled(true)
                .operationalCenter(givenCenter)
                .build();
        programRepository.save(givenProgram);

        Field givenField = Field.builder()
                .name("Field1")
                .sector(givenSector)
                .program(givenProgram)
                .build();
        Field saved = fieldRepository.save(givenField);

        //When
        fieldRepository.delete(saved);

        //Then
        Assertions.assertThat(fieldRepository.findById(saved.getId())).isNotPresent();
    }
}