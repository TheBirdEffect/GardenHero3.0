package de.artfulbird.gardenhero.repositories.operation;

import de.artfulbird.gardenhero.models.operation.OperationalCenter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active=test")
class OperationalCenterRepositoryTest {

    @Autowired
    OperationalCenterRepository operationalCenterRepository;

    @BeforeEach
    void setUp() {
        operationalCenterRepository.deleteAll();
    }

    @AfterEach
    void afterEach() {
        operationalCenterRepository.deleteAll();
    }

    @Test
    void shouldAddOperationalCenter() {
        //Given
        OperationalCenter given = OperationalCenter.builder()
                .name("Base1")
                .ipAddress("192.168.1.17")
                .build();

        //When
        OperationalCenter saved = operationalCenterRepository.save(given);

        //Then
        Assertions.assertThat(saved).isEqualTo(given);
    }

    @Test
    void shouldUpdateOperationalCenter() {
        //Given
        OperationalCenter given = OperationalCenter.builder()
                .name("Base1")
                .ipAddress("192.168.1.17")
                .build();

        OperationalCenter tempSaved = operationalCenterRepository.save(given);

        //When
        tempSaved.setName("Base0");
        OperationalCenter saved = operationalCenterRepository.save(tempSaved);

        //Then
        assertEquals(saved.getName(), "Base0");
    }

    @Test
    void shouldDeleteOperationalCenter() {
        //Given
        OperationalCenter given = OperationalCenter.builder()
                .name("Base1")
                .ipAddress("192.168.1.17")
                .build();

        OperationalCenter saved = operationalCenterRepository.save(given);

        //When
        operationalCenterRepository.delete(saved);

        //Then
        Assertions.assertThat(operationalCenterRepository.findByNameIgnoreCase("Base1")).isNotPresent();
    }
}