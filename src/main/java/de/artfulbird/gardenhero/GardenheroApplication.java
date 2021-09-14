package de.artfulbird.gardenhero;

import de.artfulbird.gardenhero.mqtt.PublishService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GardenheroApplication {

    public static void main(String[] args) {
        SpringApplication.run(GardenheroApplication.class, args);
    }

}
