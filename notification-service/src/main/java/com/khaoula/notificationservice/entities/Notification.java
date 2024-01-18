package com.khaoula.notificationservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Notification {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    private boolean seen;
}
