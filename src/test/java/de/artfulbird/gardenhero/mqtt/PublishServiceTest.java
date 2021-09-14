package de.artfulbird.gardenhero.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.artfulbird.gardenhero.models.operation.Program;
import de.artfulbird.gardenhero.mqtt.Exceptions.PublisherNotExistsException;

import org.assertj.core.api.Assertions;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active=test")
class PublishServiceTest {

    @Autowired
    PublishService publishService;

    @Test
    void shouldConnectToServer() throws PublisherNotExistsException, MqttException {
        //Given
        IMqttClient publisher = publishService.initializing();

        //Then
        Assertions.assertThat(publisher).isNotNull();
        publisher.disconnect();

    }

    @Test
    void shouldPublishMessage() throws PublisherNotExistsException {
        //Given
        String topic = "GardenHero_TestTopic";
        String message = "TestMessage";

        //When
        publishService.publishMessage(message, topic);

        //Then
        Assertions.assertThat(publishService.isConnected()).isTrue();
    }

    @Test
    void shouldPublishObject() throws PublisherNotExistsException {
        //Given
        Program givenProgram = Program.builder()
                .name("low_moisture")
                .isEnabled(false)
                .type("1")
                .moistureThresholdLow(20)
                .moistureThresholdHigh(50)
                .wateringDurationPerIntervalInSeconds(5)
                .wateringBreakPerIntervalInSeconds(10)
                .wateringFrequencyPerIntervalInSeconds(3)
                .build();

        //When
        publishService.publishObject(givenProgram, "ObjectTopic");
    }
}