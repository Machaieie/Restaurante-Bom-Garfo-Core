package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model;

import java.io.Serializable;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.enums.Tipo_Prato;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Prato")
public class Prato implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String nomeItem;
    private double precoUnitario;
    @Enumerated(EnumType.STRING)
    private Tipo_Prato tipo_Prato;

}
