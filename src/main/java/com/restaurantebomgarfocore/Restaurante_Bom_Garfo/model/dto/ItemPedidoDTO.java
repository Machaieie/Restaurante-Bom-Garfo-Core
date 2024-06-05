package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto;

import jakarta.validation.constraints.NotBlank;

public record ItemPedidoDTO(
    int quantidade, 
    Long pratoId
) {
    
}
