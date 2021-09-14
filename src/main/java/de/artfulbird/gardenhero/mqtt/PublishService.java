package de.artfulbird.gardenhero.mqtt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.artfulbird.gardenhero.mqtt.Exceptions.PublisherNotExistsException;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Getter
@Setter
@Service("PublisherService")
public class PublishService {

    private int qos = 2;
    private String broker = "tcp://localhost:1883";
    private String publisherId = UUID.randomUUID().toString();
    private boolean isConnected = false;



    IMqttClient initializing() {
        try {
            IMqttClient publisher = new MqttClient(broker, publisherId);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            publisher.connect(options);
            if (publisher.isConnected()) {
                System.out.println("Connected");
                isConnected = true;
            }
            return publisher;
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return null;
    }

    void publishMessage(String message, String topic) throws PublisherNotExistsException {
        IMqttClient publisher = initializing();
        if(publisher != null)
        {
            try {
                MqttMessage newMessage = new MqttMessage(message.getBytes());
                newMessage.setQos(qos);
                publisher.publish(topic, newMessage);
                System.out.println("Message published");
                publisher.disconnect();
                System.out.println("Disconnected");
            } catch (MqttPersistenceException e) {
                e.printStackTrace();
            } catch (MqttException e) {
                e.printStackTrace();
                System.out.println("reason "+e.getReasonCode());
                System.out.println("msg "+e.getMessage());
                System.out.println("loc "+e.getLocalizedMessage());
                System.out.println("cause "+e.getCause());
                System.out.println("excep "+e);
            }
        } else {
            throw new PublisherNotExistsException("Publisher is Null");
        }
    }

    void publishObject(Object message, String topic) throws PublisherNotExistsException {
        IMqttClient publisher = initializing();
        if(publisher != null)
        {
            ObjectMapper mapper = new ObjectMapper();
            try {
                String convertedMessage = mapper.writeValueAsString(message);

                MqttMessage newMessage = new MqttMessage(convertedMessage.getBytes());
                newMessage.setQos(qos);
                publisher.publish(topic, newMessage);
                System.out.println("Message published");
                publisher.disconnect();
                System.out.println("Disconnected");
            } catch (MqttPersistenceException | JsonProcessingException e) {
                e.printStackTrace();
            } catch (MqttException er) {
                er.printStackTrace();
                System.out.println("reason "+er.getReasonCode());
                System.out.println("msg "+er.getMessage());
                System.out.println("loc "+er.getLocalizedMessage());
                System.out.println("cause "+er.getCause());
                System.out.println("excep "+er);
            }
        } else {
            throw new PublisherNotExistsException("Publisher is Null");
        }
    }
}
