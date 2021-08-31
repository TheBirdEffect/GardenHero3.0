package de.artfulbird.gardenhero.repositories.operation;

import de.artfulbird.gardenhero.models.operation.OperationalCenter;
import de.artfulbird.gardenhero.models.operation.Pump;
import de.artfulbird.gardenhero.models.operation.PumpData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active=test")
class PumpDataRepositoryTest {

    @Autowired
    PumpDataRepository pumpDataRepository;

    @Autowired
    OperationalCenterRepository operationalCenterRepository;

    @Autowired
    PumpRepository pumpRepository;


    @BeforeEach
    void setUp() {
        pumpDataRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        pumpDataRepository.deleteAll();
    }

    @Test
    void shouldAddPumpData() {
        LocalDateTime givenBegin = LocalDateTime.now();
        LocalDateTime givenEnd = givenBegin.plusSeconds(5);

        OperationalCenter givenCenter = OperationalCenter.builder()
                .name("Base0")
                .ipAddress("192.168.1.17")
                .build();
        OperationalCenter savedCenter = operationalCenterRepository.save(givenCenter);

        Pump givenPump = Pump.builder()
                .name("Toshiba ER-1")
                .isRunning(false)
                .operationalCenter(savedCenter)
                .build();
        Pump savedPump = pumpRepository.save(givenPump);


        PumpData givenPumpData = PumpData.builder()
                .running_time_begin(givenBegin)
                .running_time_end(givenEnd)
                .average_water_consumption(0.0)
                .pump(savedPump)
                .build();

        //When
        PumpData saved = pumpDataRepository.save(givenPumpData);

        //Then
        Assertions.assertThat(saved).isEqualTo(givenPumpData);
    }

    @Test
    void shouldUpdateAverageConsumptionPumpData() {
        LocalDateTime givenBegin = LocalDateTime.now();
        LocalDateTime givenEnd = givenBegin.plusSeconds(5);

        OperationalCenter givenCenter = OperationalCenter.builder()
                .name("Base0")
                .ipAddress("192.168.1.17")
                .build();
        OperationalCenter savedCenter = operationalCenterRepository.save(givenCenter);

        Pump givenPump = Pump.builder()
                .name("Toshiba ER-1")
                .isRunning(false)
                .operationalCenter(savedCenter)
                .build();
        Pump savedPump = pumpRepository.save(givenPump);


        PumpData givenPumpData = PumpData.builder()
                .running_time_begin(givenBegin)
                .running_time_end(givenEnd)
                .average_water_consumption(0.0)
                .pump(savedPump)
                .build();
        PumpData saved = pumpDataRepository.save(givenPumpData);

        //When
        Double valueBefore = saved.getAverage_water_consumption();
        saved.setAverage_water_consumption(2.0);
        PumpData result = pumpDataRepository.save(saved);

        //Then
        Assertions.assertThat(result.getAverage_water_consumption()).isNotEqualTo(valueBefore);
    }

    @Test
    void shouldDeleteumpData() {
        LocalDateTime givenBegin = LocalDateTime.now();
        LocalDateTime givenEnd = givenBegin.plusSeconds(5);

        OperationalCenter givenCenter = OperationalCenter.builder()
                .name("Base0")
                .ipAddress("192.168.1.17")
                .build();
        OperationalCenter savedCenter = operationalCenterRepository.save(givenCenter);

        Pump givenPump = Pump.builder()
                .name("Toshiba ER-1")
                .isRunning(false)
                .operationalCenter(savedCenter)
                .build();
        Pump savedPump = pumpRepository.save(givenPump);


        PumpData givenPumpData = PumpData.builder()
                .running_time_begin(givenBegin)
                .running_time_end(givenEnd)
                .average_water_consumption(0.0)
                .pump(savedPump)
                .build();
        PumpData saved = pumpDataRepository.save(givenPumpData);

        //When
        pumpDataRepository.delete(saved);

        //Then
        Assertions.assertThat(pumpDataRepository.findById(saved.getId())).isNotPresent();
    }



}