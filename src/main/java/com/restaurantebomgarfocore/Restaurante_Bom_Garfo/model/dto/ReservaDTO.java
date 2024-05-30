package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto;

import jakarta.validation.constraints.NotBlank;

public record ReservaDTO(
    @NotBlank String date,
    @NotBlank String time,
    @NotBlank int numberPeople,
    @NotBlank String observations,
    @NotBlank long clientId
) {
    
}