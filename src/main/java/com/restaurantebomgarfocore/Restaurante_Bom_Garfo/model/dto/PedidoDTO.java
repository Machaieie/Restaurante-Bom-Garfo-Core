package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto;

import java.util.List;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.enums.StatusPedido;


public record PedidoDTO(
    Long reserva_id, 
    StatusPedido statusPedido,
    List<ItemPedidoDTO>
    itensPedido
) {
    
}
