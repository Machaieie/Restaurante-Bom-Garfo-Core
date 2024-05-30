package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Client;

public interface ClientRepository extends JpaRepository<Client,Long>{
    boolean existsByNomeAndApelido(String nome, String apelido);
   
}
