package de.artfulbird.gardenhero.repositories.operation;

import de.artfulbird.gardenhero.models.operation.OperationalCenter;
import de.artfulbird.gardenhero.models.operation.Program;
import de.artfulbird.gardenhero.models.operation.Pump;
import de.artfulbird.gardenhero.models.operation.PumpData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest(properties = "spring.profiles.active=test")
class PumpRepositoryTest {

    @Autowired
    PumpRepository pumpRepository;

    @Autowired
    OperationalCenterRepository operationalCenterRepository;

    @BeforeEach
    void setUp() {
        pumpRepository.deleteAll();
        operationalCenterRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        pumpRepository.deleteAll();
    }

    @Test
    void shouldAddPump() {
        //Given
        OperationalCenter givenCenter = OperationalCenter.builder()
                .name("Base0")
                .ipAddress("192.168.1.17")
                .build();

        OperationalCenter savedCenter = operationalCenterRepository.save(givenCenter);
        PumpData pumpData = PumpData.builder()
                .build();

        Pump givenPump = Pump.builder()
                .name("Toshiba ER-1")
                .operationalCenter(savedCenter)
                .build();
        //When
        Pump saved = pumpRepository.save(givenPump);

        //Then
        Assertions.assertThat(saved).isEqualTo(givenPump);
    }

    @Test
    void shouldUpdatePump() {
        //Given
        OperationalCenter givenCenter = OperationalCenter.builder()
                .name("Base0")
                .ipAddress("192.168.1.17")
                .build();

        OperationalCenter savedCenter = operationalCenterRepository.save(givenCenter);
        PumpData pumpData = PumpData.builder()
                .build();

        Pump givenPump = Pump.builder()
                .name("Toshiba ER-1")
                .isRunning(true)
                .operationalCenter(savedCenter)
                .build();
        Pump saved = pumpRepository.save(givenPump);

        //When
        saved.setRunning(false);
        Pump result = pumpRepository.save(saved);


        //Then
        Assertions.assertThat(result.isRunning()).isFalse();
    }


    @Test
    void shouldDeletePump() {
        //Given
        OperationalCenter givenCenter = OperationalCenter.builder()
                .name("Base0")
                .ipAddress("192.168.1.17")
                .build();

        OperationalCenter savedCenter = operationalCenterRepository.save(givenCenter);
        PumpData pumpData = PumpData.builder()
                .build();

        Pump givenPump = Pump.builder()
                .name("Toshiba ER-1")
                .isRunning(true)
                .operationalCenter(savedCenter)
                .build();
        Pump saved = pumpRepository.save(givenPump);

        //When
        pumpRepository.delete(saved);

        //Then
        Assertions.assertThat(pumpRepository.findById(saved.getId())).isNotPresent();
    }
}