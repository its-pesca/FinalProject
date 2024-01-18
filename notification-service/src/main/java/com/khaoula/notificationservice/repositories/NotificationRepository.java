package com.khaoula.notificationservice.repositories;

import com.khaoula.notificationservice.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
