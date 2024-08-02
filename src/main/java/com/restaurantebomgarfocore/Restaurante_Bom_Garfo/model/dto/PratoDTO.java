package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.enums.Tipo_Prato;

public record PratoDTO(
     Long id,
     String nomeItem,
     Double precoUnitario,
     Tipo_Prato tipo_Prato
) {
    
}
