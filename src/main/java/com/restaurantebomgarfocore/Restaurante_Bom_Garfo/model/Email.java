package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model;


import java.io.Serializable;
import java.time.LocalDateTime;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.enums.EmailStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Email")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Email implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendDateEmail;
    private EmailStatus statusEmail;

}