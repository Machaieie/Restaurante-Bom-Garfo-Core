package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String firstName;
    private String lastName;
    private String username;
    private String  phone;
    private String email;
    private Set<UserRole> roles;
    private String token;
}
