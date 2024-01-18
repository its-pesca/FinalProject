package com.khaoula.notificationservice.kafka;

import com.khaoula.notificationservice.entities.Notification;
import com.khaoula.notificationservice.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @Autowired
    NotificationRepository notificationRepository;

    @KafkaListener(topics = "acheteur",groupId = "group")
    public void consume(String message){
        notificationRepository.save(Notification.builder().message(message).build());
    }

}
