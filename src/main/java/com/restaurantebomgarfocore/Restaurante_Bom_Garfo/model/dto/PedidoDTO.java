package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto;

import java.util.List;


public record PedidoDTO(
    Long reserva_id, 
    List<ItemPedidoDTO>
    itensPedido
) {
    
}
