package com.orange.innovation.dronemanagement.schedule;

import com.orange.innovation.dronemanagement.service.DroneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class DroneBatteryTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final DroneService droneService;

    public DroneBatteryTask(KafkaTemplate<String, String> kafkaTemplate, DroneService droneService) {
        this.kafkaTemplate = kafkaTemplate;
        this.droneService = droneService;
    }

    @Scheduled(fixedRate = 1000)
    public void reportCurrentTime() {
        List<String> dronesBatteries = droneService.getAllDrones().stream().map(drone -> "Drone ID: "+drone.getId() + ", Drone Battery Health: " + drone.getBatteryPercentage()).toList();
        String message;
        log.info((message = dateFormat.format(new Date()) + " -> " + dronesBatteries));
        kafkaTemplate.send("droneBatteryLoggingTopic", message);
    }
}
