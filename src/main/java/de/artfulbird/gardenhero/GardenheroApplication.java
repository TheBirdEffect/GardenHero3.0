package de.artfulbird.gardenhero;

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

        String[] topics = {"/sector/1/field/1", "/sector/2/field/2", "/sector/3/field/3"};
        int[] qos = {2,1,0};

        SubscribeService subscribeService = new SubscribeService();
        subscribeService.subscribe(topics, qos);
    }

}
