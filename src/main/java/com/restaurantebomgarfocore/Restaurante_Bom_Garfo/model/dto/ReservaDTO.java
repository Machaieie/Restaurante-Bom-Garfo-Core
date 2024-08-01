package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
public record ReservaDTO(
    @NotBlank String date,
    @NotBlank String time,
    @Min(1) int numberPeople,
    @NotBlank String observations,
    String observations,
    @NotBlank String firstName,
    @NotBlank String lastName,
    @Email @NotBlank String email,
    @NotBlank String phone
) {}
