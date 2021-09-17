package de.artfulbird.gardenhero.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.artfulbird.gardenhero.models.measurement.Field;
import de.artfulbird.gardenhero.models.measurement.Moisture;
import de.artfulbird.gardenhero.models.operation.Program;
import de.artfulbird.gardenhero.mqtt.Exceptions.PublisherNotExistsException;

import de.artfulbird.gardenhero.repositories.measurement.MoistureRepository;
import org.assertj.core.api.Assertions;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

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

    @Test
    void shouldPublishMoisture() throws PublisherNotExistsException, ParseException {
        //Given
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        String toParse = LocalDateTime.now().toString();
        /*Date date = dateFormat.parse(toParse);
        System.out.println(date);*/



        Field field = Field.builder()
                .name("Jade")
                .build();

        Moisture moisture = Moisture.builder()
                .value(90)
                .measurement_time(LocalDateTime.now())
                .field(field)
                .build();

        ObjectMapper mapper = new ObjectMapper();

        //When
        publishService.publishObject(moisture, "sectors/fields/moisture/");
    }

    @Test
    void shouldPublishTest() throws PublisherNotExistsException, ParseException {
        //Given
        de.artfulbird.gardenhero.models.measurement.Test test = de.artfulbird.gardenhero.models.measurement.Test.builder()
                .name("User")
                .age(4)
                .build();

        ObjectMapper mapper = new ObjectMapper();

        //When
        publishService.publishObject(test, "sectors/fields/moisture/");
    }
}