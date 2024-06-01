package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model;

import java.io.Serializable;
import java.util.List;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.enums.StatusPedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Pedido")
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private StatusPedido statusPedido;
    

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itensPedido;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;
    
    public void addItemPedido(ItemPedido item) {
        itensPedido.add(item);
        item.setPedido(this);
    }
    
    public void removeItemPedido(ItemPedido item) {
        itensPedido.remove(item);
        item.setPedido(null);
    }
}
