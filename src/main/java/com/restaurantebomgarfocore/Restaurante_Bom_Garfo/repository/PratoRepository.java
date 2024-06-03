package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Prato;

@Repository
public interface PratoRepository extends JpaRepository<Prato,Long>{
    
}