package de.artfulbird.gardenhero.repositories.operation;

import de.artfulbird.gardenhero.models.operation.OperationalCenter;
import de.artfulbird.gardenhero.models.operation.Program;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active=test")
class ProgramRepositoryTest {

    @Autowired
    ProgramRepository programRepository;

    @Autowired
    OperationalCenterRepository operationalCenterRepository;

    @BeforeEach
    void beforeAll() {
        programRepository.deleteAll();
        operationalCenterRepository.deleteAll();
    }

    @AfterEach
    void afterEach() {
        programRepository.deleteAll();
    }

    @Test
    void shouldAddProgram() {
        //Given
        OperationalCenter givenCenter = OperationalCenter.builder()
                .name("Base0")
                .ipAddress("192.168.1.17")
                .build();
        operationalCenterRepository.save(givenCenter);

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

        //When
        Program saved = programRepository.save(givenProgram);

        //Then
        Assertions.assertThat(saved).isEqualTo(givenProgram);
    }

    @Test
    void shouldChangeMoistureThresholdHigh() {
        //Given
        OperationalCenter givenCenter = OperationalCenter.builder()
                .name("Base0")
                .ipAddress("192.168.1.17")
                .build();
        operationalCenterRepository.save(givenCenter);

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

        Program saved = programRepository.save(givenProgram);

        //When
        saved.setMoistureThresholdHigh(65);
        Program result = programRepository.save(saved);

        //Then
        Assertions.assertThat(result.getMoistureThresholdHigh()).isEqualTo(saved.getMoistureThresholdHigh());
    }

    @Test
    void shouldDeleteProgram() {
        //Given
        OperationalCenter givenCenter = OperationalCenter.builder()
                .name("Base0")
                .ipAddress("192.168.1.17")
                .build();
        operationalCenterRepository.save(givenCenter);

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
        Program saved = programRepository.save(givenProgram);

        //When
        programRepository.delete(saved);

        //Then
        Assertions.assertThat(programRepository.findByNameIgnoreCase("Dry")).isNotPresent();
    }

    @Test
    void shouldFindProgramByNearThresholdMoistureHigh() {
        //Given
        OperationalCenter givenCenter = OperationalCenter.builder()
                .name("Base0")
                .ipAddress("192.168.1.17")
                .build();
        operationalCenterRepository.save(givenCenter);

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

        //When
        Program saved = programRepository.save(givenProgram);

        //Then
        Assertions.assertThat(programRepository.findProgramByMoistureThresholdHighIsLessThanEqual(80)).isPresent();
    }

    @Test
    void findProgramWhichMoistureThresholdHighIsBetween() {
        //Given
        OperationalCenter givenCenter = OperationalCenter.builder()
                .name("Base0")
                .ipAddress("192.168.1.17")
                .build();
        operationalCenterRepository.save(givenCenter);

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

        //When
        Program saved = programRepository.save(givenProgram);

        //Then
        Assertions.assertThat(programRepository.findProgramByMoistureThresholdHighIsBetween(60, 80)).isPresent();
    }

    @Test
    void shouldFindProgramByNearThresholdMoistureLow() {
        //Given
        OperationalCenter givenCenter = OperationalCenter.builder()
                .name("Base0")
                .ipAddress("192.168.1.17")
                .build();
        operationalCenterRepository.save(givenCenter);

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

        //When
        Program saved = programRepository.save(givenProgram);

        //Then
        Assertions.assertThat(programRepository.findProgramByMoistureThresholdLowIsGreaterThanEqual(40)).isPresent();
    }

    @Test
    void findProgramWhichMoistureThresholdLowIsBetween() {
        //Given
        OperationalCenter givenCenter = OperationalCenter.builder()
                .name("Base0")
                .ipAddress("192.168.1.17")
                .build();
        operationalCenterRepository.save(givenCenter);

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

        //When
        Program saved = programRepository.save(givenProgram);

        //Then
        Assertions.assertThat(programRepository.findProgramByMoistureThresholdLowIsBetween(45, 60)).isPresent();
    }


}