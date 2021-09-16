package de.artfulbird.gardenhero.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.artfulbird.gardenhero.models.measurement.Field;
import de.artfulbird.gardenhero.models.measurement.Moisture;
import de.artfulbird.gardenhero.models.measurement.Sector;
import de.artfulbird.gardenhero.models.operation.PumpData;
import de.artfulbird.gardenhero.repositories.measurement.FieldRepository;
import de.artfulbird.gardenhero.repositories.measurement.MoistureRepository;
import de.artfulbird.gardenhero.repositories.measurement.SectorRepository;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.DataInput;
import java.util.Optional;

@Component
public class CallbackDetails implements MqttCallback {

    @Autowired
    MoistureRepository moistureRepository;

    @Autowired
    FieldRepository fieldRepository;

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        //System.out.println("Valid topic hit; Topic: " + topic);
        System.out.println("Topic: " + topic.startsWith("sectors/fields/moisture"));
        ObjectMapper objectMapper = new ObjectMapper();
        if(topic.startsWith("sectors/fields/moisture")){
            System.out.println(mqttMessage);
            Moisture moisture = objectMapper.readValue((DataInput) mqttMessage, Moisture.class);
            System.out.println("Moisture" + moisture);
            Optional<Field> field = fieldRepository.findById(moisture.getField().getId());
            System.out.println("Valid topic hit; Topic: " + topic);
            if(field.isPresent()){
                moisture.setField(field.get());
                moistureRepository.save(moisture);
                System.out.println("Data stored!");
            }
        } else if(topic.startsWith("/pumps/pumpData")){
            PumpData pumpData = objectMapper.readValue((DataInput)mqttMessage, PumpData.class);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
