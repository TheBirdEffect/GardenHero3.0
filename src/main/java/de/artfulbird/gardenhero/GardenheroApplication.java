package de.artfulbird.gardenhero;

import de.artfulbird.gardenhero.models.operation.OperationalCenter;
import de.artfulbird.gardenhero.mqtt.CallbackDetails;
import de.artfulbird.gardenhero.mqtt.PublishService;
import de.artfulbird.gardenhero.mqtt.SubscribeService;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.auth.callback.Callback;
import java.util.UUID;

@SpringBootApplication
public class GardenheroApplication {

    public static MqttAsyncClient client;

    public static void main(String[] args) throws MqttException {
        SpringApplication.run(GardenheroApplication.class, args);

        String[] topics = {"sectors/#", "pump/#", "prog/#"};
        int[] qos = {2, 2, 2};

        SubscribeService subscribeService = new SubscribeService();
        subscribeService.subscribe(topics, qos);

    }

}
