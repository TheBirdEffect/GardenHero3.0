package de.artfulbird.gardenhero;

import de.artfulbird.gardenhero.models.operation.OperationalCenter;
import de.artfulbird.gardenhero.mqtt.PublishService;
import de.artfulbird.gardenhero.mqtt.SubscribeService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GardenheroApplication {

    public static void main(String[] args) throws MqttException {
        SpringApplication.run(GardenheroApplication.class, args);

        String[] topics = {"sectors/#", "pump/#"};
        int[] qos = {2, 2};

        SubscribeService subscribeService = new SubscribeService();
        subscribeService.subscribe(topics, qos);

    }

}
