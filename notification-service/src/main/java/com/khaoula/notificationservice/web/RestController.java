package com.khaoula.notificationservice.web;

import com.khaoula.notificationservice.entities.Notification;
import com.khaoula.notificationservice.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    NotificationRepository notificationRepository;

    @GetMapping("/notifications")
    public List<Notification> getAll(){
        return notificationRepository.findAll();
    }

    @PutMapping("/notifications/{id}")
    public Notification updateNotification(@PathVariable Integer id){
        Notification n = notificationRepository.findById(id).get();
        n.setSeen(true);
        return notificationRepository.save(n);
    }
}
