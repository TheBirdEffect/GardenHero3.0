package de.artfulbird.gardenhero.mqtt;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@Component
public class SubscribeService {
    private int qos = 2;
    private String broker = "tcp://localhost:1883";
    private String publisherId = UUID.randomUUID().toString();
    private boolean isConnected = false;

    public static MqttAsyncClient subscriber;

    public void subscribe(String[] topics, int[] qos) throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        subscriber = new MqttAsyncClient(broker, publisherId, persistence);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(100);
        options.setCleanSession(true);

        CallbackDetails callBack = new CallbackDetails();
        subscriber.setCallback(callBack);


        IMqttToken token = subscriber.connect();
        token.waitForCompletion();

        //subscriber.subscribe(topic, qos);
        subscriber.subscribe(topics, qos);






        if(subscriber.isConnected()){
            System.out.println("Subscriber " + publisherId + " connected!" );
        } else if (!subscriber.isConnected()){
            System.out.println("Subscriber " + publisherId + " disconnected!");
        }
    }

}
