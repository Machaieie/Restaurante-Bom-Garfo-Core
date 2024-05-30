package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Email;

public interface EmailRepository extends JpaRepository<Email, Long>{
    
}
