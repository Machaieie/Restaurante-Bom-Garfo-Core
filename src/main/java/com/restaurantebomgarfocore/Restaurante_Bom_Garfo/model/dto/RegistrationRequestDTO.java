package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.enums.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrationRequestDTO(
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotBlank String username,
    @NotBlank String password,
    @NotBlank String phone,
    @NotBlank String email,
    @NotNull Role role
) {
    
}
