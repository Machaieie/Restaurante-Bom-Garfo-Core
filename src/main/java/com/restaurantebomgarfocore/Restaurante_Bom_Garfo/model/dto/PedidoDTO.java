package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public record PedidoDTO(
    @NotEmpty long reserva_id,
    List<ItemPedidoDTO> itensPedido
) {
    
}
