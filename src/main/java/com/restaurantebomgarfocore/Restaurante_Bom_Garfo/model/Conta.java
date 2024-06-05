package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Conta")
public class Conta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double total;

    @OneToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    public double calcularTotal() {
        
        for (Pedido pedido : reserva.getPedidos()) {
            for (ItemPedido item : pedido.getItensPedido()) {
                total += item.getQuantidade() * item.getPrato().getPrecoUnitario();
            }
        }
        return total;
    }
    
}
